package cn.edu.bistu.cs; /**
 * 
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 *
 */
public class TestMain {

	private static final String data = "In computer science and information theory, "
			+ "a Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. "
			+ "The process of finding and/or using such a code proceeds by means of Huffman coding, "
			+ "an algorithm developed by David A. Huffman while he was a Ph.D. student at MIT, and published in the 1952 paper "
			+ "\"A Method for the Construction of Minimum-Redundancy Codes\".[1] "
			+ "The output from Huffman's algorithm can be viewed as a variable-length code table for encoding a source symbol "
			+ "(such as a character in a file). The algorithm derives this table from the estimated probability or frequency of occurrence"
			+ " (weight) for each possible value of the source symbol. As in other entropy encoding methods, more common symbols are generally "
			+ "represented using fewer bits than less common symbols. Huffman's method can be efficiently implemented, "
			+ "finding a code in linear time to the number of input weights if these weights are sorted.[2] However, "
			+ "although optimal among methods encoding symbols separately, Huffman coding is not always optimal among all compression methods.";
	
	private static void testBasic(){
		HuffmanTree htree = new HuffmanTree();	
		// 首先对字符串中的字符出现次数进行统计
		Map<Character, Integer> chars = HuffmanTree.computeCharCount(data);
		long length = data.length();
		ArrayList<HTNode> nodes = new ArrayList<>();
		for (Character c : chars.keySet()) {
			HTNode node = new HTNode();
			node.setData(c);
			node.setWeight(chars.get(c));
			node.setLchild(null);
			node.setRchild(null);
			node.setDepth(0);// 所有叶子结点的深度都为0
			node.setLeaf(true);// 初始结点都是叶子结点，合并后的结点都不是叶子结点
			nodes.add(node);
		}
		htree.buildTree(nodes);
		Map<Character, String> code = htree.getCode();
		double weightedLength = 0;// 哈夫曼树编码的加权长度
		double theoryWeightedLength = 0;// 根据信息熵公式计算得到的理论长度
		double p = 0;
		for (Character c : code.keySet()) {
			System.out.println("字符'"+c+"'的哈夫曼编码："+code.get(c));
			p = (chars.get(c) * 1.0) / length;
			weightedLength += code.get(c).length() * p;
			theoryWeightedLength += p * Math.log(p);
		}
		System.out.println(weightedLength);
		System.out.println(-theoryWeightedLength);
		String text = "abcd";
		String coded = htree.encode(text);
		System.out.println("字符串：" + text);
		System.out.println("被编码为：" + coded);
		String oriText = htree.decode(coded);
		System.out.println("编码：" + coded);
		System.out.println("被解码为：" + oriText);
		System.out.println(oriText.equals(text));
		System.out.println("编码后二进制字节长度:" + (1.0 * coded.length()) / 8);
		System.out.println("原字符串长度" + text.length());
	}
	
	private static void testSer() throws IOException {
		HuffmanTree htree = new HuffmanTree();
		// 首先对字符串中的字符出现次数进行统计
		Map<Character, Integer> chars = HuffmanTree.computeCharCount(data);
		ArrayList<HTNode> nodes = new ArrayList<>();
		for (Character c : chars.keySet()) {
			HTNode node = new HTNode();
			node.setData(c);
			node.setWeight(chars.get(c));
			node.setLchild(null);
			node.setRchild(null);
			node.setDepth(0);// 所有叶子结点的深度都为0
			node.setLeaf(true);// 初始结点都是叶子结点，合并后的结点都不是叶子结点
			nodes.add(node);
		}
		htree.buildTree(nodes);
		HTSerDes serde = new HTSerDes();
		serde.serToFile(htree, "", "htree");
	}
	private static void testDes() throws IOException, ClassNotFoundException {
		HuffmanTree htree = null;
		Map<Character, Integer> chars = HuffmanTree.computeCharCount(data);
		long length = data.length();
		HTSerDes serde = new HTSerDes();
		htree = (HuffmanTree) serde.desFromFile("", "htree");
		Map<Character, String> code = htree.getCode();
		double weightedLength = 0;// 哈夫曼树编码的加权长度
		double theoryWeightedLength = 0;// 根据信息熵公式计算得到的理论长度
		double p = 0;
		/*for (Character c : code.keySet()) {
			System.out.println("字符'"+c+"'的哈夫曼编码："+code.get(c));
			p = (chars.get(c) * 1.0) / length;
			weightedLength += code.get(c).length() * p;
			theoryWeightedLength += p * Math.log(p);
		}*/
		System.out.println(weightedLength);
		System.out.println(-theoryWeightedLength);
		String text = "abcd";
		String coded = htree.encode(text);
		System.out.println("字符串：" + text);
		System.out.println("被编码为：" + coded);
		String oriText = htree.decode(coded);
		System.out.println("编码：" + coded);
		System.out.println("被解码为：" + oriText);
		//System.out.println(oriText.equals(text));
		//System.out.println("编码后二进制字节长度:" + (1.0 * coded.length()) / 8);
		//System.out.println("原字符串长度" + text.length());
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//TestMain.testBasic();//基本测试（实验二）
		TestMain.testSer();//序列化测试（实验三）
		TestMain.testDes();//反序列化测试（实验三）
	}
}
