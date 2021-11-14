package util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple Str Operation
 * 
 * @author lq
 * 
 */
public class SSO {

	/**
	 * test the string is null or empty,if not null and not empty return true
	 * 
	 * @param str
	 * @return istnoe
	 */
	public static boolean tnoe(String str) {
		boolean istnoe = false;
		if ((str != null) && (!(str.trim().equals("")))) {
			istnoe = true;
		}

		return istnoe;
	}

	/**
	 * test the string is null or empty,if null or empty return true
	 * 
	 * @param str
	 * @return istnoe
	 */
	public static boolean tioe(String str) {
		boolean istioe = false;
		if ((str == null) || (str.trim().equals(""))) {
			istioe = true;
		}

		return istioe;
	}

	public static String implode(String[] seg_arr, String separator) {
		String ns = "";
		if (seg_arr.length < 1) {
			return "";
		}
		for (int i = 0; i < (seg_arr.length - 1); i++) {
			ns = ns + seg_arr[i] + separator;
		}
		ns = ns + seg_arr[seg_arr.length - 1];

		return ns;
	}

	public static String implode(ArrayList arr_list, String separator) {
		String ns = "";
		if ((arr_list.size()) < 1) {
			return "";
		}
		String lit = "";
		for (int i = 0; i < ((arr_list.size()) - 1); i++) {
			lit = arr_list.get(i) + "";
			if (!(SSO.tnoe(lit))) {
				continue;
			}

			ns = ns + lit + separator;
		}
		ns = ns + arr_list.get(arr_list.size() - 1);

		return ns;
	}

	/**
	 * trunk substr before str,not including str
	 * 
	 * @param str
	 * @return
	 */
	public static String beforeStr(String source, String str) {
		String ns = "";
		if (!(SSO.tnoe(source))) {
			return "";
		}
		if (source.indexOf(str) < 0) {
			return source;
		}
		ns = source.substring(0, source.indexOf(str));
		ns = ns.trim();
		return ns;
	}

	/**
	 * trunk substr after str,not including str
	 * 
	 * @param str
	 * @return
	 */
	public static String afterStr(String source, String str) {
		String ns = "";
		if (!(SSO.tnoe(source))) {
			return "";
		}
		if (source.indexOf(str) < 0) {
			return source;
		}
		ns = source.substring(source.indexOf(str) + str.length(), source.length());
		ns = ns.trim();
		return ns;
	}

	/**
	 * trunk substr after str,including str
	 * 
	 * @param source
	 * @param str
	 * @return
	 */
	public static String truncAfterStr(String source, String str) {
		String ts = "";
		if (source.indexOf(str) < 0) {
			return source;
		}
		ts = source.substring(0, source.lastIndexOf(str));
		ts = ts.trim();
		return ts;
	}

	/**
	 * trunk substr before str,including str
	 * 
	 * @param source
	 * @param str
	 * @return
	 */
	public static String truncBeforeStr(String source, String str) {
		String ts = "";
		if (source.indexOf(str) < 0) {
			return source;
		}
		ts = source.substring(source.indexOf(str) + str.length(), source.length());
		ts = ts.trim();
		return ts;
	}

	/**
	 * return substr between str1,str2,not including str1,str2(str1 first
	 * occurrence,str2 last occurrence)
	 * 
	 * @param source
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String midstrs(String source, String str1, String str2) {
		String ms = "";
		if (!(SSO.tnoe(source))) {
			return "";
		}
		int firstIndex = 0;
		int lastIndex = 0;

		if ((source.indexOf(str1)) == (source.lastIndexOf(str2))) {
			return source;
		}
		if (source.indexOf(str1) < 0) {
			firstIndex = 0;
		} else {
			firstIndex = source.indexOf(str1) + str1.length();
		}

		if (source.lastIndexOf(str2) < 0) {
			lastIndex = source.length();
		} else {
			lastIndex = source.lastIndexOf(str2);
		}

		// System.out.println("source:"+source+" fi:"+firstIndex+"
		// li:"+lastIndex);
		ms = source.substring(firstIndex, lastIndex);
		ms = ms.trim();
		return ms;
	}

	public static void midstrs(String source, String str1, String str2, List<String> matches) {
		String ms = "";
		if (!(SSO.tnoe(source))) {
			return;
		}
		int firstIndex = 0;
		int lastIndex = 0;

		if ((source.indexOf(str1)) == (source.indexOf(str2))) {
			return;
		}

		if (source.indexOf(str1) < 0) {
			return;
		} else {
			firstIndex = source.indexOf(str1) + str1.length();
		}

		if (source.indexOf(str2) < 0) {
			return;
		} else {
			lastIndex = source.indexOf(str2);
		}

		 //System.out.println("source:"+source+" fi:"+firstIndex+" li:"+lastIndex);
		if (firstIndex > lastIndex) {
			source = source.substring(source.indexOf(str2) + str2.length(), source.length());
			midstrs(source, str1, str2, matches);
		}

		if (firstIndex < lastIndex)
			ms = source.substring(firstIndex, lastIndex);
		else
			ms = "";
		ms = ms.trim();
		if (SSO.tioe(ms)) {
			return;
		}

		matches.add(ms);

		midstrs(source.substring(lastIndex + str2.length(), source.length()), str1, str2, matches);

	}

	/**
	 * return substr between str1,str2,not including str1,str2(str1 first
	 * occurrence,str2 first occurrence)
	 * 
	 * @param source
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String midfstrs(String source, String str1, String str2) {
		String ms = "";
		if (!(SSO.tnoe(source))) {
			return "";
		}
		int firstIndex = 0;
		int lastIndex = 0;

		// System.out.println("findex:"+source.indexOf(str1)+"
		// sindex:"+source.indexOf(str2));
		if ((source.indexOf(str1)) == (source.indexOf(str2))) {
			return source;
		}
		if (source.indexOf(str1) < 0) {
			return "";
		} else {
			firstIndex = source.indexOf(str1);
			source = source.substring(firstIndex, source.length());
			firstIndex = source.indexOf(str1) + str1.length();
		}

		if (source.indexOf(str2) < 0) {
			return "";
		} else {
			lastIndex = source.indexOf(str2);
		}

		// System.out.println("source:"+source+" fi:"+firstIndex+"
		// li:"+lastIndex);
		if (firstIndex < 0 || lastIndex < 0 || firstIndex > lastIndex) {
			return "";
		}

		ms = source.substring(firstIndex, lastIndex);
		ms = ms.trim();

		return ms;
	}

	/**
	 * replace 'find' in source by 'replace'
	 * 
	 * @param source
	 * @param find
	 * @param replace
	 * @return
	 */
	public static String replaceLast(String source, String find, String replace) {
		find = find.trim();
		if (!(SSO.tnoe(find))) {
			return source;
		}
		if (source.lastIndexOf(find) < 0) {
			return "";
		}
		String prefix = source.substring(0, source.lastIndexOf(find));
		String suffix = source.substring(source.lastIndexOf(find) + find.length(), source.length());
		String ns = prefix + replace + suffix;
		return ns;
	}

	public static String[] sepFirst(String source, String seprator) {
		String[] pairArr = new String[2];
		if (SSO.tioe(source)) {
			return null;
		}
		source = source.trim();

		String[] tokens = source.split(seprator);
		pairArr[0] = tokens[0];

		pairArr[1] = "";
		for (int j = 1; j < tokens.length; j++) {
			if (seprator.equals("\\s+")) {
				pairArr[1] += (tokens[j] + " ");
			} else {
				pairArr[1] += (tokens[j] + seprator);
			}
		}
		pairArr[1] = pairArr[1].trim();

		return pairArr;
	}

	public static String[] getResponseType(String response_value) {
		String[] responseArr = new String[2];
		responseArr[0] = "";
		responseArr[1] = "";
		String responseType = "";
		String responseSplitVal = "";

		String rt_regex = "((?:(?:A)|(?:MX)|(?:AAAA)|(?:TXT)|(?:CNAME)|(?:SOA)|(?:NS))\\s*[a-zA-Z0-9_\\-\\.\\s\\,:]*?)\\s*(?:(?:A)|(?:MX)|(?:AAAA)|(?:TXT)|(?:CNAME)|(?:SOA)|(?:NS))";
		ArrayList rt_list = new ArrayList();
		Pattern rt_pat = Pattern.compile(rt_regex);
		Matcher rt_mat = rt_pat.matcher(response_value);
		String sin_type = "";
		String left_value = "";
		boolean isFind = false;
		while (rt_mat.find()) {
			isFind = true;
			sin_type = rt_mat.group(1);
			rt_list.add(sin_type);
			response_value = response_value.replaceFirst(sin_type, "");
			// System.out.println("response_value:"+response_value);
			left_value = response_value;
			rt_mat = rt_pat.matcher(response_value);
		}
		if (isFind == false) {
			left_value = response_value;
		}
		left_value = left_value.replaceAll("\\([\\d]+\\)", "");
		left_value = left_value.trim();
		// System.out.println("left_value: "+left_value.trim());
		rt_list.add(left_value);

		String[] seg_arr = null;

		for (int i = 0; i < (rt_list.size()); i++) {
			sin_type = rt_list.get(i) + "";
			responseSplitVal = responseSplitVal + sin_type + "|";
			sin_type = sin_type.trim();
			if (!(SSO.tnoe(sin_type))) {
				continue;
			}
			seg_arr = sin_type.split("\\s+");
			if ((seg_arr == null) || (seg_arr.length < 1)) {
				continue;
			}

			// System.out.println(i+" "+seg_arr[0]);
			responseType = responseType + seg_arr[0] + "|";
		}
		if (SSO.tnoe(responseType)) {
			responseType = responseType.substring(0, responseType.lastIndexOf("|"));
		}
		if (SSO.tnoe(responseSplitVal)) {
			responseSplitVal = responseSplitVal.substring(0, responseSplitVal.lastIndexOf("|"));
		}
		// System.out.println("responseType: "+responseType);
		// responseType=responseType+"|"+rt_list.get((rt_list.size()-1));
		responseArr[0] = responseType;
		responseArr[1] = responseSplitVal;
		return responseArr;
	}

	/**
	 * ngram ,at least two char
	 * 
	 * @param word
	 * @return
	 */
	public static ArrayList<String> ngram(String word) {

		ArrayList<String> ngrams = new ArrayList<String>();
		if (word.length() <= 2) {
			ngrams.add(word);
			return ngrams;
		}

		if ((Pattern.matches("[a-zA-Z%0-9\\\\\\\\_\\.]*", word))) {
			ngrams.add(word);
			return ngrams;
		}

		int i = 0;
		int j = 2;
		String gram = "";
		for (i = 0; i <= (word.length() - 2); i++) {
			for (j = i + 2; j <= word.length(); j++) {
				gram = word.substring(i, j);
				ngrams.add(gram);
			}
		}

		return ngrams;
	}

	/**
	 * ngram ,at least bottom char
	 * 
	 * @param word
	 * @return
	 */
	public static ArrayList<String> ngram(String word, int bottom) {

		ArrayList<String> ngrams = new ArrayList<String>();
		if (word.length() <= bottom) {
			ngrams.add(word);
			return ngrams;
		}

		if ((Pattern.matches("[a-zA-Z%0-9\\\\\\\\_\\.]*", word))) {
			ngrams.add(word);
			return ngrams;
		}

		int i = 0;
		int j = 2;
		String gram = "";
		for (i = 0; i <= (word.length() - bottom); i++) {
			for (j = i + bottom; j <= word.length(); j++) {
				gram = word.substring(i, j);
				ngrams.add(gram);
			}
		}

		return ngrams;
	}
	
	public static ArrayList<String> ngramWords(String phrase, int bottom) {

		ArrayList<String> ngrams = new ArrayList<String>();
		
		if(SSO.tioe(phrase))
		{
			return ngrams;
		}
		
		phrase=phrase.trim();
		
		String[] words=phrase.split("\\s+");
		
		if (words.length <= bottom) {
			ngrams.add(phrase);
			return ngrams;
		}


		int i = 0;
		int j = 2;
		String gram = "";
		for (i = 0; i <= (words.length - bottom); i++) {
			for (j = i + bottom; j <= words.length; j++) {
				gram = substring(words,i, j);
				ngrams.add(gram);
			}
		}

		return ngrams;
	}
	
	public static String substring(String[] words,int i,int j)
	{
		String phrase="";
		int k;
		for(k=i;k<j;k++)
		{
			phrase+=(words[k]+" ");
		}
		
		return phrase.trim();
	}

	public static String fm_tag(String tagWords) {
		if (tagWords == null || tagWords.trim().equals("")) {
			return "";
		}

		tagWords = tagWords.trim();

		String[] tokens = tagWords.split("\\s+");

		String token = "", w = "", tag = "";

		String blankWords = "";

		for (int i = 0; i < tokens.length; i++) {
			token = tokens[i];
			w = token.substring(0, token.lastIndexOf("#"));
			tag = token.substring(token.lastIndexOf("#") + 1, token.length());
			if (tag.equals("PU")) {
				w = "PU";
			}

			blankWords += (w + " ");
		}

		blankWords = blankWords.trim();

		String[] frags = blankWords.split("PU");

		String frag = "";

		List<String> fm_list = null;

		HashMap<String, Integer> statWords = new HashMap<String, Integer>();

		String temp = "";

		for (int i = 0; i < frags.length; i++) {
			frag = frags[i];
			if (frag == null || frag.trim().equals("")) {
				continue;
			}

			frag = frag.trim();

			fm_list = fm(frag);

			if (fm_list == null) {
				continue;
			}

			for (int j = 0; j < fm_list.size(); j++) {
				temp = fm_list.get(j);
				if (!(statWords.containsKey(temp))) {
					statWords.put(temp, 1);
				} else {
					statWords.put(temp, statWords.get(temp) + 1);
				}
			}

		}

		String result = "";
		for (Map.Entry<String, Integer> entry : statWords.entrySet()) {
			result += (entry.getKey() + ":" + entry.getValue() + " ");
		}

		return result.trim();
	}

	public static List<String> fm(String words) {
		if (words == null || words.trim().equals("")) {
			return null;
		}

		words = words.trim();

		String fm_words = "";

		String[] tokens = words.split("\\s+");

		String fm_word = "";

		int concat = 3;

		List<String> fm_list = new ArrayList<String>();

		for (int i = 0; i < tokens.length; i++) {
			for (int j = i + 1; j <= (i + concat) && j <= tokens.length; j++) {
				fm_words = "";
				for (int k = i; k < j; k++) {
					fm_words += tokens[k];
				}
				fm_list.add(fm_words);

			}
		}

		return fm_list;
	}
	
	public static boolean startWithUpperCase(String word)
	{
		if(SSO.tioe(word))
			return false;
		word=word.trim();
		if(Character.isUpperCase(word.charAt(0)))
		{
		   return true;	
		}
		
		return false;
	}

	public static void main(String[] args) {
		/*
		 * String date_str="11:30:43";
		 * date_str=date_str.substring(0,date_str.lastIndexOf(":"));
		 * date_str=date_str.replaceAll(":", "");
		 * System.out.println("date_str:"+date_str); String s="";
		 * 
		 * System.out.println("s:"+s.trim()); s=null;
		 * System.out.println("s2:"+s);
		 * 
		 * String s2=
		 * "CNAME us.sina.com.cn., us.sina.com.cn. CNAME news.sina.com.cn., news.sina.com.cn. CNAME jupiter.sina.com.cn., jupiter.sina.com.cn. CNAME taurus.sina.com.cn., taurus.sina.com.cn. A 61.172.201.20, taurus.sina.com.cn. A 61.172.201.21, taurus.sina.com.cn. A 61.172.201.24, taurus.sina.com.cn. A 61.172.201.25, taurus.sina.com.cn. A 61.172.201.36, taurus.sina.com.cn. A 61.172.201.9, taurus.sina.com.cn. A 61.172.201.10, taurus.sina.com.cn. A 61.172.201.11, taurus.sina.com.cn. A 61.172.201.12, taurus.sina.com.cn. A 61.172.201.13, taurus.sina.com.cn. A 61.172.201.14, taurus.sina.com.cn. A 61.172.201.15, taurus.sina.com.cn. A 61.172.201.16, taurus.sina.com.cn. A 61.172.201.17, taurus.sina.com.cn. A 61.172.201.18, taurus.sina.com.cn. A 61.172.201.19 ns: sina.com.cn. NS ns2.sina.com.cn., sina.com.cn. NS ns3.sina.com.cn., sina.com.cn. NS ns1.sina.com.cn., sina.com.cn. NS ns4.sina.com.cn. ar: ns1.sina.com.cn. A 202.106.184.166, ns2.sina.com.cn. A 61.172.201.254, ns3.sina.com.cn. A 123.125.29.99, ns4.sina.com.cn. A 121.14.1.22 (512)"
		 * ; String[] resarr=SSO.getResponseType(s2);
		 * System.out.println("0:"+resarr[0]);
		 * System.out.println("1:"+resarr[1]);
		 * 
		 * 
		 * String s3=
		 * "2014-03-02 07:00:00 : {\"locid\":31,\"guestid\":\"e37d710cbf13b1081c849cc0cb56a9b7\",\"user_agent\":\"Mozilla\\\\/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident\\\\/4.0; .NET CLR 2.0.50727)\",\"host\":\"www.189so.cn\",\"refer\":\"\",\"title\":\"\u5bfc\u822a\",\"ip\":\"115.203.69.65\",\"cm\":1,\"num\":1,\"ids\":[\"730096d3a1f29d549c90efca81129950\"]}"
		 * ; String time=SSO.beforeStr(s3, "{");
		 * System.out.println("time:"+time); String
		 * time2=SSO.truncAfterStr(time, ":");
		 * System.out.println("time2:"+time2);
		 * 
		 * String info=SSO.midstrs(s3, "{", "}");
		 * System.out.println("info:"+info);
		 * 
		 * String[] seg_arr=info.split(",");
		 * 
		 * String key=""; String val="";
		 * 
		 * for(int i=0;i<seg_arr.length;i++) { key=SSO.beforeStr(seg_arr[i],
		 * ":"); key=SSO.midstrs(key, "\"", "\""); val=SSO.afterStr(seg_arr[i],
		 * ":"); val=SSO.midstrs(val, "\"", "\"");
		 * 
		 * System.out.println(i+" "+seg_arr[i]+"   key="+key+"  val="+val); }
		 */
		/*
		 * String source="乡村爱情圆舞曲48"; String find="48"; String replace="";
		 * System.out.println(replaceLast(source,find,replace));
		 */
		/*
		 * String source = "铜仁市";
		 * System.out.println(source.charAt(source.length() - 1));
		 * 
		 * String str="Thread-7";
		 * System.out.println("str:"+str.replaceAll("Thread\\-", ""));
		 */
		/*
		 * String source="学生椅子"; ArrayList<String> list=ngram(source); for(int
		 * i=0;i<list.size();i++) { System.out.println("i="+i+" "+list.get(i));
		 * }
		 */
		// String
		// source="source:/tcref.php?src=http%3A%2F%2Fm.58.com%2Fnb%2Fsiji%2F&word=58%E5%90%8C%E5%9F%8E%E7%BD%91%E5%AE%81%E6%B3%A2%E6%8B%9B%E8%81%98%E5%8F%B8%E6%9C%BA&di=f5c40a80ed72d357201abef30200233b
		// fi:57 li:51";

		// System.out.println(SSO.midfstrs(source, "word=", "&"));

		//String tagWords = "祝勇#AD 。#PU 为#P 沈从文#NR 百年#VV 诞辰#NN 而#MSP 写#VV 的#DEC 专题#NN 散文#NN 。#PU /#PU 论#VV 史实#NN ，#PU 这#PN 书#NN 说#VV 得#DER 远#VA 不#AD 及#VV 《#PU 湖南#NR 凤凰#NR 》#PU 那#DT 一本#M 详实#NN 。#PU 书#NN 中#LC 很多#CD 文字#NN ，#PU 其实#AD 都#AD 可#VV 算作#VV 沈从文#NR 作品#NN 读后感#NN ；#PU 可是#AD 读后感#NN .#PU .#PU .#PU .#PU .#PU .#PU 我#PN 自己#PN 也#AD 会#VV 写#VV .#PU .#PU .#PU .#PU .#PU .#PU /#PU 这#DT 书#NN 唯一#JJ 的#DEG 亮#VA 点#AD 是#VC 陈渠珍#NR 和#CC 藏女西#NR 原#JJ 的#DEG 故事#NN ，#PU 传奇#VA 而#CC 深情#VA 。#PU 不过#AD 会#VV 有#VE 这样#PN 的#DEG 感觉#NN 是#VC 建立#VV 在#P 我#PN 此前#AD 未曾#AD 读#VV 过#AS 陈渠珍#NR 自己#PN 写#VV 的#DEC 《#PU 艽#NN 野#JJ 尘#NN 梦#NN 》#PU �#NN";

		// String words="奎 因 和 阿加莎 克 里 斯蒂 的 书 读 起来 总 没有 福尔摩斯";
		//String fm_words = SSO.fm_tag(tagWords);

		//System.err.println("fm_words:" + fm_words);

		//String word="excellent artists";
		//System.err.println(SSO.startWithUpperCase(word));
		
		System.err.println(SSO.ngramWords("in the world", 1));
		
	}

}