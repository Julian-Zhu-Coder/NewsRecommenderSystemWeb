package test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.WordDictionary;
import com.qianxinyao.analysis.jieba.keyword.Keyword;
import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;

import dao.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.Segment;

public class JiebaTest {
//  java.util.HashMap<K,V>
//K - the type of keys maintained by this map
//V - the type of mapped values
//    很好了解决了该txt中一行信息，包括关键词和权重
    Map<String, Double> idfmap = new HashMap<String, Double>();
	
	public void set_idf(List<String> list){

		long startTime = System.currentTimeMillis();
		System.out.println("开始计时：");		
		long d = (System.currentTimeMillis() - startTime)/1000;
		long minute = d / 60;
		long second = d % 60;		
		System.out.println("读取数据完成，耗时：" + minute + "分" + second + "秒");
		long startTime2 = System.currentTimeMillis();

        // 生成一个Segmenter分词对象
		JiebaSegmenter segmenter = new JiebaSegmenter();
		for(int i=0;i<1000;i++){
			System.out.print(i+"  ");
			// 调用分词方法 segmenter.process(字符串,分词模式)，返回一个SegToken对象的列表
			List<SegToken> tokenList = segmenter.process(list.get(i), SegMode.SEARCH);
			for(SegToken token:tokenList){
				// 遍历列表，从列表中每个SegToken对象中，获得word。
				System.out.print("\t"+token.word);
			}
			System.out.println("");
		}


		d = (System.currentTimeMillis() - startTime2)/1000;
		minute = d / 60;
		second = d % 60;		
		System.out.println("分词1000条完成，耗时：" + minute + "分" + second + "秒");
	}
	
	public static void main(String[] args) {		
		
//		Demo参考代码————————————————————————————————————————————————————————————————————————————————————————————————————————————————
//		String s = "在解决“两不愁三保障”突出问题座谈会上，他指出脱贫既要看数量，更要看质量。贫困县摘帽不摘责任、不摘政策、不摘帮扶、不摘监管；在第六个国家扶贫日到来之际，他作出重要指示，要求各地区各部门务必咬定目标、一鼓作气，坚决攻克深度贫困堡垒；在中央政治局常委会会议专门研究“三农”工作时他指出，脱贫质量怎么样、小康成色如何，很大程度上要看明年“三农”工作成效。要集中资源、强化保障、精准施策，加快补上“三农”领域短板";
//		// 生成一个Segmenter分词对象
//		JiebaSegmenter segmenter = new JiebaSegmenter();
//		// 调用分词方法 segmenter.process(需要分词的字符串 ,分词模式)，返回的是一个SegToken对象的列表
//		List<SegToken> tokenList = segmenter.process(s, SegMode.SEARCH);
//		for(SegToken token:tokenList){
//			// 遍历列表，从列表中每个SegToken对象中，获得word。
//			System.out.println(token.word);
//		}

//        String content="孩子上了幼儿园 安全防拐教育要做好";
//        int topN=5;
//        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
//        List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
//        for(Keyword word:list)
//            System.out.println(word.getName()+" "+word.getTfidfvalue()+",");
//		——————————————————————————————————————————————————————————————————————————————————————————————————————
		ArticleDao articleDao = new ArticleDao();		//new一个ArticleDao对象
		List<String> titleList = articleDao.getTitleList();	//调用ArticleDao对象的getTitleList，返回List<String>

		TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();                       //	Segmenter分词对象segmenter，
		int topN=5;		//提取五个关键词
		List<HashSet> hashSetList = null;
		HashSet set = null;
//		循环list列表，用tfidfAnalyzer.analyze处理分词，使用停用词，去重逻辑，保存在List<HashSet>，
		for(int i = 0;i < titleList.size();i++) {
			List<Keyword> list = tfidfAnalyzer.analyze(titleList.get(i), topN);
			for (Keyword word:list) {
				set.add(word.getName());
			}
			hashSetList.add(set);
			set.clear();
		}
		//把每篇文章标题提取关键字用HashSet保存
	//把HashSet插入列表中。

//		双重循环，遍历List<HashSet>每个集合的元素（词项），处理包含每个词 word 的文档数，返回List<HashMap> 01
//		HashMap map = null;
//		List<HashMap> hashMapList = null;
//		for (int i=0; i < hashSetList.size(); i++) {
//			for (int j = 0;j < hashSetList.get(j).size(); j++) {
////				若List<HashMap>不存在该词项，则新建HashMap，V初始为1
//				System.out.println(hashSetList.get(i).toString());
////				若List<HashMap>存在该词项，则在该HashMap的V加一
//			}
//		}
	}

}
