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
//    �ܺ��˽���˸�txt��һ����Ϣ�������ؼ��ʺ�Ȩ��
    Map<String, Double> idfmap = new HashMap<String, Double>();
	
	public void set_idf(List<String> list){

		long startTime = System.currentTimeMillis();
		System.out.println("��ʼ��ʱ��");		
		long d = (System.currentTimeMillis() - startTime)/1000;
		long minute = d / 60;
		long second = d % 60;		
		System.out.println("��ȡ������ɣ���ʱ��" + minute + "��" + second + "��");
		long startTime2 = System.currentTimeMillis();

        // ����һ��Segmenter�ִʶ���
		JiebaSegmenter segmenter = new JiebaSegmenter();
		for(int i=0;i<1000;i++){
			System.out.print(i+"  ");
			// ���÷ִʷ��� segmenter.process(�ַ���,�ִ�ģʽ)������һ��SegToken������б�
			List<SegToken> tokenList = segmenter.process(list.get(i), SegMode.SEARCH);
			for(SegToken token:tokenList){
				// �����б����б���ÿ��SegToken�����У����word��
				System.out.print("\t"+token.word);
			}
			System.out.println("");
		}


		d = (System.currentTimeMillis() - startTime2)/1000;
		minute = d / 60;
		second = d % 60;		
		System.out.println("�ִ�1000����ɣ���ʱ��" + minute + "��" + second + "��");
	}
	
	public static void main(String[] args) {		
		
//		Demo�ο����롪������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
//		String s = "�ڽ���������������ϡ�ͻ��������̸���ϣ���ָ����ƶ��Ҫ����������Ҫ��������ƶ����ժñ��ժ���Ρ���ժ���ߡ���ժ�������ժ��ܣ��ڵ��������ҷ�ƶ�յ���֮�ʣ���������Ҫָʾ��Ҫ����������������ҧ��Ŀ�ꡢһ������������������ƶ�����ݣ����������ξֳ�ί�����ר���о�����ũ������ʱ��ָ������ƶ������ô����С����ɫ��Σ��ܴ�̶���Ҫ�����ꡰ��ũ��������Ч��Ҫ������Դ��ǿ�����ϡ���׼ʩ�ߣ��ӿ첹�ϡ���ũ������̰�";
//		// ����һ��Segmenter�ִʶ���
//		JiebaSegmenter segmenter = new JiebaSegmenter();
//		// ���÷ִʷ��� segmenter.process(��Ҫ�ִʵ��ַ��� ,�ִ�ģʽ)�����ص���һ��SegToken������б�
//		List<SegToken> tokenList = segmenter.process(s, SegMode.SEARCH);
//		for(SegToken token:tokenList){
//			// �����б����б���ÿ��SegToken�����У����word��
//			System.out.println(token.word);
//		}

//        String content="���������׶�԰ ��ȫ���ս���Ҫ����";
//        int topN=5;
//        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
//        List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
//        for(Keyword word:list)
//            System.out.println(word.getName()+" "+word.getTfidfvalue()+",");
//		������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
		ArticleDao articleDao = new ArticleDao();		//newһ��ArticleDao����
		List<String> titleList = articleDao.getTitleList();	//����ArticleDao�����getTitleList������List<String>

		TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();                       //	Segmenter�ִʶ���segmenter��
		int topN=5;		//��ȡ����ؼ���
		List<HashSet> hashSetList = null;
		HashSet set = null;
//		ѭ��list�б���tfidfAnalyzer.analyze����ִʣ�ʹ��ͣ�ôʣ�ȥ���߼���������List<HashSet>��
		for(int i = 0;i < titleList.size();i++) {
			List<Keyword> list = tfidfAnalyzer.analyze(titleList.get(i), topN);
			for (Keyword word:list) {
				set.add(word.getName());
			}
			hashSetList.add(set);
			set.clear();
		}
		//��ÿƪ���±�����ȡ�ؼ�����HashSet����
	//��HashSet�����б��С�

//		˫��ѭ��������List<HashSet>ÿ�����ϵ�Ԫ�أ�������������ÿ���� word ���ĵ���������List<HashMap> 01
//		HashMap map = null;
//		List<HashMap> hashMapList = null;
//		for (int i=0; i < hashSetList.size(); i++) {
//			for (int j = 0;j < hashSetList.get(j).size(); j++) {
////				��List<HashMap>�����ڸô�����½�HashMap��V��ʼΪ1
//				System.out.println(hashSetList.get(i).toString());
////				��List<HashMap>���ڸô�����ڸ�HashMap��V��һ
//			}
//		}
	}

}
