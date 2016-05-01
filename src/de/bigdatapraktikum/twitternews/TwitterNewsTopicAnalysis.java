package de.bigdatapraktikum.twitternews;



import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.graph.Graph;
import org.apache.flink.types.NullValue;
import org.apache.flink.util.Collector;

import de.bigdatapraktikum.twitternews.processing.EdgeMapper;
import de.bigdatapraktikum.twitternews.processing.IdMapper;
import de.bigdatapraktikum.twitternews.processing.IdfValueCalculator;
import de.bigdatapraktikum.twitternews.processing.UniqueWordMapper;
import de.bigdatapraktikum.twitternews.processing.UniqueWordsIdfJoin;
import de.bigdatapraktikum.twitternews.utils.AppConfig;

public class TwitterNewsTopicAnalysis {
	public static void main(String[] args) throws Exception {
		// set up the execution environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// get input data from previously stored twitter data
		DataSource<String> tweets = env.readTextFile(AppConfig.TWEET_STORAGE_PATH);
		DataSet<Tuple2<Long,String>> tweetsWithID = tweets.map(new IdMapper());
		// Calculates the number of tweets
		double amountOfTweets = tweets.count();

		// TODO Tweets are currently strings (semicolon seperated values) and
		// need to be converted to Tweet objects first
		
		//TODO Seperate IDF Calculation and Graph Creation
		
		// Calculates occurance for all the unique words. Excludes the
		// irrelevant words that are defined in the AppConfig.java
		DataSet<Tuple3<Long, String, Integer>> uniqueWordsinTweets = tweetsWithID.flatMap(new UniqueWordMapper(AppConfig.IRRELEVANT_WORDS));
		
		DataSet<Tuple2<String, Integer>> tweetFrequency = uniqueWordsinTweets.map(new MapFunction<Tuple3<Long,String,Integer>, Tuple2<String, Integer>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<String, Integer> map(Tuple3<Long, String, Integer> input) throws Exception {
				
				return new Tuple2<>(input.f1,input.f2);
			}
			
		}).groupBy(0).sum(1);
		// Prints all the Unique words with their occurance in descending order
		tweetFrequency.filter(new FilterFunction<Tuple2<String, Integer>>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Tuple2<String, Integer> word) throws Exception {

				return word.f1 > 50;
			}
		}).sortPartition(1, Order.DESCENDING).print();

		// Calculates the IDF Values for all the words
		DataSet<Tuple2<String, Double>> idfValues = tweetFrequency.map(new IdfValueCalculator(amountOfTweets));
		idfValues.filter(new FilterFunction<Tuple2<String, Double>>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Tuple2<String, Double> word) throws Exception {

				return word.f1 < 3;
			}
		});
		
		// Prints all IDF Values
		idfValues.sortPartition(1, Order.DESCENDING).print();
		DataSet<Tuple2<Long, String>> filterdWordsinTweets = uniqueWordsinTweets.join(idfValues.filter(new FilterFunction<Tuple2<String, Double>>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Tuple2<String, Double> word) throws Exception {

				return word.f1 < 3;
			}
		})).where(1).equalTo(0).with(new UniqueWordsIdfJoin()).sortPartition(0, Order.ASCENDING);
		
		DataSet<Tuple2<Long,String>> aggregatedWordsinTweets = filterdWordsinTweets.groupBy(0).reduce(new ReduceFunction<Tuple2<Long,String>>() {
			
			@Override
			public Tuple2<Long, String> reduce(Tuple2<Long, String> in1, Tuple2<Long, String> in2) throws Exception {
				
				return new Tuple2<>(in1.f0, in1.f1 +";" + in2.f1);
			}
		});
		DataSet<Tuple3<String, String, Integer>> edges = aggregatedWordsinTweets.flatMap(new EdgeMapper()).groupBy(0,1).sum(2);
		Graph<String, NullValue, Integer> graph = Graph.fromTupleDataSet(edges, env);
		System.out.println();
		
		
		// TODO either save data in sink or process it further within this class

		// run application
		env.execute();
	}
}
