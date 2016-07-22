package de.bigdatapraktikum.twitternews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.flink.api.common.aggregators.Aggregator;
import org.apache.flink.api.common.aggregators.LongSumAggregator;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.io.TextOutputFormat.TextFormatter;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.core.fs.FileSystem.WriteMode;
import org.apache.flink.graph.Edge;
import org.apache.flink.graph.Graph;
import org.apache.flink.graph.Vertex;
import org.apache.flink.graph.gsa.ApplyFunction;
import org.apache.flink.graph.gsa.GatherFunction;
import org.apache.flink.graph.gsa.Neighbor;
import org.apache.flink.graph.gsa.SumFunction;
import org.apache.flink.graph.library.CommunityDetection;
import org.apache.flink.graph.library.GSAConnectedComponents;
import org.apache.flink.graph.library.LabelPropagation;
import org.apache.flink.graph.library.Summarization;
import org.apache.flink.graph.spargel.MessageIterator;
import org.apache.flink.graph.spargel.MessagingFunction;
import org.apache.flink.graph.spargel.VertexUpdateFunction;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.LongValue;
import org.apache.flink.types.MapValue;

import de.bigdatapraktikum.twitternews.processing.ChineseWhisperInitialClassMapper;
import de.bigdatapraktikum.twitternews.processing.EdgeMapper;
import de.bigdatapraktikum.twitternews.processing.TweetFilter;
import de.bigdatapraktikum.twitternews.source.Tweet;
import de.bigdatapraktikum.twitternews.utils.AppConfig;

// this class creates a co-occurrence graph
public class TwitterNewsGraphCreator {
	private static Graph<String, Long, Double> graph;
	private static List<Vertex<String, Long>> verticleList;

	public static void main(String[] args) throws Exception {
			
		TweetFilter tweetFilter = new TweetFilter();
		// tweetFilter.setDateFrom(LocalDateTime.now().minusDays(7));
		// tweetFilter.setDateTo(LocalDateTime.now().minusHours(0));

		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		
		
//		
//		DataSet<Tuple2<Integer, String>> test = env.fromCollection(test1).map(new MapFunction<Integer, Tuple2<Integer, String>>() {
//
//			@Override
//			public Tuple2<Integer, String> map(Integer arg0) throws Exception {
//				// TODO Auto-generated method stub
//				return new Tuple2<>(arg0, "Test" + arg0);
//			}
//		});
//		test.sortPartition(0, Order.ASCENDING).print();
		
				
		// returns the co-occurrence graph with the help of the
		// TwitterNewsTopicAnalysis
		// public Graph<String, NullValue, Integer> getCoOccurrenceGraph()
		// throws Exception{

		// get the filtered tweets
		TwitterNewsTopicAnalysis twitterNewsTopicAnalysis = new TwitterNewsTopicAnalysis();
		DataSet<Tuple2<Tweet, ArrayList<String>>> wordsPerTweet = twitterNewsTopicAnalysis.getFilteredWordsInTweets(env,
				tweetFilter);

		// create the graph
		DataSet<Tuple3<String, String, Double>> edges = wordsPerTweet.flatMap(new EdgeMapper()).groupBy(0, 1).sum(2);
		
		graph = Graph.fromTupleDataSet(edges, new ChineseWhisperInitialClassMapper(), env);

		// get the strongest connection between two nodes
		double maxEdgeCount = graph.getEdges().max(2).collect().get(0).f2;
		
		graph.getEdges().writeAsFormattedText(AppConfig.RESOURCES_GRAPH_EDGES, WriteMode.OVERWRITE,
				new TextFormatter<Edge<String, Double>>() {
					private static final long serialVersionUID = 1L;

					@Override
					public String format(Edge<String, Double> value) {
						double weight = Math.min(value.f2 / maxEdgeCount, 1);
						int colorIntensityR = (int) (180. + (75 * weight));
						int colorIntensityG = (int) (180. * (1. - weight));
						int colorIntensityB = (int) (110. * (1. - weight));
						return "{\"data\":{\"source\":\"" + value.f0 + "\",\"target\":\"" + value.f1 + "\",\"weight\":"
								+ weight + "},\"group\":\"edges\",\"style\":{\"line-color\":\"rgb(" + colorIntensityR
								+ ", " + colorIntensityG + "," + colorIntensityB + ")\"}},";
					}
				});


		verticleList = graph.getVertices().collect();
		Graph<String, Long, Double> run1 = graph.run(new CommunityDetection<String>(AppConfig.maxIterations, AppConfig.delta));
		run1.getVertices().print();	

		env.execute();
	}
	
	

	
	
}

