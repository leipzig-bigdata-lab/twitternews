package de.bigdatapraktikum.twitternews.config;

public class AppConfig {
	public static final int STATUS_OK = 200;
	public static final int STATUS_INTERNAL_ERROR = 500;

	// deprecated
	public static final double MAX_IDF_VALUE = 2.0;
	public static final int NUMBER_OF_NODES = 50;

	public static final int maxIterations = 3;
	public static final double delta = 0.5;

	public static final String RESOURCES_TWEETS = "src/main/resources/web/resources/tweets.txt";
	public static final String RESOURCES_TWEETS_FILTERED = "src/main/resources/web/resources/filtered_tweets";
	public static final String RESOURCES_GRAPH_NODES = "src/main/resources/web/resources/nodes.txt";
	public static final String RESOURCES_GRAPH_EDGES = "src/main/resources/web/resources/edges.txt";
	public static final String RESOURCES_GRAPH_CLUSTER = "src/main/resources/web/resources/cluster.txt";
	public static final String RESOURCES_GRAPH_HISTORICAL = "src/main/resources/web/resources/historical.csv";

	public static final String[] TWITTER_ACCOUNTS_TO_CRAWL = new String[] { "faznet", "SPIEGELONLINE", "SZ",
			"jungewelt" };
	public static final String[] TWITTER_ACCOUNTS_READABLE = new String[] { "FAZ.NET", "SPIEGEL ONLINE",
			"Süddeutsche Zeitung", "junge Welt" };

	public static final String[] IRRELEVANT_WORDS = new String[] { "aber", "abermals", "abgerufen", "abgerufene",
			"abgerufener", "abgerufenes", "ähnlich", "alle", "allein", "allem", "allemal", "allen", "allenfalls",
			"allenthalben", "aller", "allerdings", "allerlei", "alles", "allesamt", "allgemein", "allmählich", "allzu",
			"als", "alsbald", "also", "alt", "am", "an", "andauernd", "andere", "anderem", "anderen", "anderer",
			"andererseits", "anderes", "andern", "andernfalls", "anders", "anerkannt", "anerkannte", "anerkannter",
			"anerkanntes", "angesetzt", "angesetzte", "angesetzter", "anscheinend", "anstatt", "auch", "auf",
			"auffallend", "aufgrund", "aufs", "augenscheinlich", "aus", "ausdrücklich", "ausdrückt", "ausdrückte",
			"ausgedrückt", "ausgenommen", "ausgerechnet", "ausnahmslos", "außen", "außer", "außerdem", "außerhalb",
			"äußerst", "bald", "bei", "beide", "beiden", "beiderlei", "beides", "beim", "beinahe", "bekannt",
			"bekannte", "bekannter", "bekanntlich", "bereits", "besonders", "besser", "bestenfalls", "bestimmt",
			"beträchtlich", "bevor", "bezüglich", "bin", "bis", "bisher", "bislang", "bist", "bloß", "Bsp", "bzw", "ca",
			"Co", "da", "dabei", "dadurch", "dafür", "dagegen", "daher", "dahin", "damals", "damit", "danach",
			"daneben", "dank", "danke", "dann", "dannen", "daran", "darauf", "daraus", "darf", "darfst", "darin",
			"darüber", "darum", "darunter", "das", "dass", "dasselbe", "davon", "davor", "dazu", "dein", "deine",
			"deinem", "deinen", "deiner", "deines", "dem", "demgegenüber", "demgemäß", "demnach", "demselben", "den",
			"denen", "denkbar", "denn", "dennoch", "denselben", "der", "derart", "derartig", "deren", "derer",
			"derjenige", "derjenigen", "derselbe", "derselben", "derzeit", "des", "deshalb", "desselben", "dessen",
			"desto", "deswegen", "dich", "die", "diejenige", "dies", "diese", "dieselbe", "dieselben", "diesem",
			"diesen", "dieser", "dieses", "diesmal", "diesseits", "dir", "direkt", "direkte", "direkten", "direkter",
			"doch", "dort", "dorther", "dorthin", "drin", "drüber", "drunter", "du", "dunklen", "durch", "durchaus",
			"durchweg", "eben", "ebenfalls", "ebenso", "ehe", "eher", "eigenen", "eigenes", "eigentlich", "ein", "eine",
			"einem", "einen", "einer", "einerseits", "eines", "einfach", "einig", "einige", "einigem", "einigen",
			"einiger", "einigermaßen", "einiges", "einmal", "einseitig", "einseitige", "einseitigen", "einseitiger",
			"einst", "einstmals", "einzig", "e. K.", "entsprechend", "entweder", "er", "ergo", "erhält", "erheblich",
			"erneut", "erst", "ersten", "es", "etc", "etliche", "etwa", "etwas", "euch", "euer", "eure", "eurem",
			"euren", "eurer", "eures", "falls", "fast", "ferner", "folgende  ", "folgenden", "folgender",
			"folgendermaßen", "folgendes", "folglich", "förmlich", "fortwährend", "fraglos", "Frau", "frei", "freie",
			"freies", "freilich", "für", "gab", "gängig", "gängige", "gängigen", "gängiger", "gängiges", "ganz",
			"ganze", "ganzem", "ganzen", "ganzer", "ganzes", "gänzlich", "gar", "GbR", "GbdR", "geehrte", "geehrten",
			"geehrter", "gefälligst", "gegen", "gehabt", "gekonnt", "gelegentlich", "gemacht", "gemäß", "gemeinhin",
			"gemocht", "genau", "genommen", "genügend", "genug", "geradezu", "gern", "gestern", "gestrige", "getan",
			"geteilt", "geteilte", "getragen", "gewesen", "gewiss", "gewisse", "gewissermaßen", "gewollt", "geworden",
			"ggf", "gib", "gibt", "gleich", "gleichsam", "gleichwohl", "gleichzeitig", "glücklicherweise", "GmbH",
			"Gott sei Dank", "größtenteils", "Grunde", "gute", "guten", "hab", "habe", "halb", "hallo", "halt", "hast",
			"hat", "hatte", "hätte", "hätte", "hätten", "hattest", "hattet", "häufig", "heraus", "herein", "heute",
			"heutige", "hier", "hiermit", "hiesige", "hin", "hinein", "hingegen", "hinlänglich", "hinten", "hinter",
			"hinterher", "hoch", "höchst", "höchstens", "ich", "ihm", "ihn", "ihnen", "ihr", "ihre", "ihrem", "ihren",
			"ihrer", "ihres", "im", "immer", "immerhin", "immerzu", "in", "indem", "indessen", "infolge",
			"infolgedessen", "innen", "innerhalb", "ins", "insbesondere", "insofern", "insofern", "inzwischen",
			"irgend", "irgendein", "irgendeine", "irgendjemand", "irgendwann", "irgendwas", "irgendwen", "irgendwer",
			"irgendwie", "irgendwo", "ist", "ja", "jährig", "jährige", "jährigen", "jähriges", "je", "jede", "jedem",
			"jeden", "jedenfalls", "jeder", "jederlei", "jedes", "jedoch", "jemals", "jemand", "jene", "jenem", "jenen",
			"jener", "jenes", "jenseits", "jetzt", "kam", "kann", "kannst", "kaum", "kein", "keine", "keinem", "keinen",
			"keiner", "keinerlei", "keines", "keines", "keinesfalls", "keineswegs", "KG", "klar", "klare", "klaren",
			"klares", "klein", "kleinen", "kleiner", "kleines", "konkret", "konkrete", "konkreten", "konkreter",
			"konkretes", "können", "könnt", "konnte", "könnte", "konnten", "könnten", "künftig", "lag", "lagen",
			"langsam", "längst", "längstens", "lassen", "laut", "lediglich", "leer", "leicht", "leider", "lesen",
			"letzten", "letztendlich", "letztens", "letztes", "letztlich", "lichten", "links", "Ltd", "mag", "magst",
			"mal", "man", "manche", "manchem", "manchen", "mancher", "mancherorts", "manches", "manchmal", "Mann",
			"mehr", "mehrere", "mehrfach", "mein", "meine", "meinem", "meinen", "meiner", "meines", "meinetwegen",
			"meist", "meiste", "meisten", "meistens", "meistenteils", "meta", "mich", "mindestens", "mir", "mit",
			"mithin", "mitunter", "möglich", "mögliche", "möglichen", "möglicher", "möglicherweise", "möglichst",
			"morgen", "morgige", "muss", "müssen", "musst", "müsst", "musste", "müsste", "müssten", "nach", "nachdem",
			"nachher", "nachhinein", "nächste", "nämlich", "naturgemäß", "natürlich", "neben", "nebenan", "nebenbei",
			"nein", "neu", "neue", "neuem", "neuen", "neuer", "neuerdings", "neuerlich", "neues", "neulich", "nicht",
			"nichts", "nichtsdestotrotz", "nichtsdestoweniger", "nie", "niemals", "niemand", "nimm", "nimmer", "nimmt",
			"nirgends", "nirgendwo", "noch", "nötigenfalls", "nun", "nunmehr", "nur", "ob", "oben", "oberhalb",
			"obgleich", "obschon", "obwohl", "oder", "offenbar", "offenkundig", "offensichtlich", "oft", "ohne",
			"ohnedies", "OHG", "OK", "partout", "per", "persönlich", "plötzlich", "praktisch", "pro", "quasi", "recht",
			"rechts", "regelmäßig", "reichlich", "relativ", "restlos", "richtiggehend", "riesig", "rund", "rundheraus",
			"rundum", "sämtliche", "sattsam", "schätzen", "schätzt", "schätzte", "schätzten", "schlechter", "schlicht",
			"schlichtweg", "schließlich", "schlussendlich", "schnell", "schonschwerlich", "schwierig", "sehr", "sei",
			"seid", "sein", "seine", "seinem", "seinen", "seiner", "seines", "seit", "seitdem", "Seite", "Seiten",
			"seither", "selber", "selbst", "selbstredend", "selbstverständlich", "selten", "seltsamerweise", "sich",
			"sicher", "sicherlich", "sie", "siehe", "sieht", "sind", "so", "sobald", "sodass", "soeben", "sofern",
			"sofort", "sog", "sogar", "solange", "solch", "solche", "solchem", "solchen", "solcher", "solches", "soll",
			"sollen", "sollst", "sollt", "sollte", "sollten", "solltest", "somit", "sondern", "sonders", "sonst",
			"sooft", "soviel", "soweit", "sowie", "sowieso", "sowohl", "sozusagen", "später", "spielen", "startet",
			"startete", "starteten", "statt", "stattdessen", "steht", "stellenweise", "stets", "tat", "tatsächlich",
			"tatsächlichen", "tatsächlicher", "tatsächliches", "teile", "total", "trotzdem", "übel", "über", "überall",
			"überallhin", "überaus", "überdies", "überhaupt", "üblicher", "übrig", "übrigens", "um", "umso",
			"umstandshalber", "umständehalber", "unbedingt", "unbeschreiblich", "und", "unerhört", "ungefähr",
			"ungemein", "ungewöhnlich", "ungleich", "unglücklicherweise", "unlängst", "unmaßgeblich", "unmöglich",
			"unmögliche", "unmöglichen", "unmöglicher", "unnötig", "uns", "unsagbar", "unsäglich", "unser", "unsere",
			"unserem", "unseren", "unserer", "unseres", "unserm", "unstreitig", "unten", "unter", "unterbrach",
			"unterbrechen", "unterhalb", "unwichtig", "unzweifelhaft", "usw", "vergleichsweise", "vermutlich",
			"veröffentlichen", "veröffentlicht", "veröffentlichte", "veröffentlichten", "veröffentlichtes", "viel",
			"viele", "vielen", "vieler", "vieles", "vielfach", "vielleicht", "vielmals", "voll", "vollends", "völlig",
			"vollkommen", "vollständig", "vom", "von", "vor", "voran", "vorbei", "vorgestern", "vorher", "vorne",
			"vorüber", "während", "währenddessen", "wahrscheinlich", "wann", "war", "wäre", "waren", "wären", "warst",
			"warum", "was", "weder", "weg", "wegen", "weidlich", "weil", "Weise", "weiß", "weitem", "weiter", "weitere",
			"weiterem", "weiteren", "weiterer", "weiteres", "weiterhin", "weitgehend", "welche", "welchem", "welchen",
			"welcher", "welches", "wem", "wen", "wenig", "wenige", "weniger", "wenigstens", "wenn", "wenngleich", "wer",
			"werde", "werden", "werdet", "weshalb", "wessen", "wichtig", "wie", "wieder", "wiederum", "wieso",
			"wiewohl", "will", "willst", "wir", "wird", "wirklich", "wirst", "wo", "wodurch", "wogegen", "woher",
			"wohin", "wohingegen", "wohl", "wohlgemerkt", "wohlweislich", "wollen", "wollt", "wollte", "wollten",
			"wolltest", "wolltet", "womit", "womöglich", "woraufhin", "woraus", "worin", "wurde", "würde", "würden",
			"z. B.", "zahlreich", "zeitweise", "ziemlich", "zu", "zudem", "zuerst", "zufolge", "zugegeben", "zugleich",
			"zuletzt", "zum", "zumal", "zumeist", "zur", "zurück", "zusammen", "zusehends", "zuvor", "zuweilen", "zwar",
			"zweifellos", "zweifelsfrei", "zweifelsohne", "zwischen", "haben", "ab", "via", "geht", "schon", "macht",
			"fordert", "zwei", "kommt", "machen", "zeigt", "sagen", "worden", "rt", "sagt", "eins", "zwei", "drei",
			"vier", "fünf", "sechs", "sieben", "acht", "neun", "dpa", "faznet", "freuters", "fazsport", "wurden", "gut",
			"kommen", "findet", "lässt", "kommentiert", "spiegelonline", "trotz", "schreibt", "erste", "erklärt",
			"bleibt", "afp", "spiegelsport", "sz", "derspiegel", "setzen", "gehen", "berichtet", "fazwoche", "dürfen",
			"fragen", "bringen" };
};
