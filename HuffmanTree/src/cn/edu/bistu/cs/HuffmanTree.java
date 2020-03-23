package cn.edu.bistu.cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 哈夫曼树实现
 * @author
 *
 */
public class  HuffmanTree {

	/**
	 * 哈夫曼编码
	 */
	private Map<Character, String> code = null;
	
	/**
	 * 生成的huffman树根结点
	 */
	private HTNode tree = null;
		
	/**
	 * 根据初始的结点列表，建立哈夫曼树，
	 * 并生成哈夫曼编码，保存在当前类的code对象中，
	 * 生成的树根结点，被保存在当前类的tree对象中。
	 * 可以反复生成哈夫曼树，每次重新构建树，将更新编码
	 * @param nodes
	 * @return
	 */
	public HTNode buildTree(List<HTNode> nodes){
		//根据map来创建树,用哈夫曼结点创建哈夫曼树,返回根节点
		//每次取两个最小的
		print(nodes);//遍历之后，知道了内容
		nodes.sort(null);
		//HtNode中的内容实现了Comparable的函数，所以可以排序
		//目的是找到它们之中最小的两个创建树
		print(nodes);
		while(nodes.size() > 1)
		{//当把所有叶子节点合并之后停止
			nodes.sort(null);
			//每次都要把最小的排在前面，所以每次添加和删除之后都要排序

			HTNode first = nodes.get(0);
			HTNode second = nodes.get(1);
			//找出两个最小的，分别赋值

			HTNode newNode = new HTNode();
			//合并两个最小的
			newNode.setLchild(first);
			newNode.setRchild(second);
			//设置左孩子是first，右孩子是second
			newNode.setLeaf(false);
			//不是叶子结点
			newNode.setWeight(first.getWeight() + second.getWeight());
			//权重是之前两个最小的叶子结点的和

			nodes.add(newNode);
			//把新合并的结点加入到最后
			nodes.remove(0);
			nodes.remove(0);
			// 把原来的两个结点删掉,删掉0，原来的1也变成0

			//System.out.println(newNode.getWeight());
			//测试权重值
		}
		return nodes.get(0);
	}

	private void print(List<HTNode> nodes)
	{//打印链表
		System.out.println("\nThe list contains: ");
		for(HTNode c : nodes)
		{
			System.out.println(c.getData() + " " + String.format("%.0f", c.getWeight()));
			//打印元素的data和value
		}
	}

	/**
	 * 根据已建立的哈夫曼树根结点，生成对应的字符编码，
	 * 字符编码应为0，1字符串
	 * @param tree
	 * @return
	 */
	public static Map<Character, String> getCode(HTNode tree){
		//TODO
		return null;
	}
	
	/**
	 * 获取已建立的哈夫曼树生成的字符编码，
	 * 字符编码应为0，1字符串
	 * @return
	 */
	public Map<Character, String> getCode(){
		return this.code;
	}
	
	
	/**
	 * 统计字符串中字符出现的频率
	 * @param text
	 * @return
	 */
	public static Map<Character,Integer> computeCharCount(String text){
		//首先实现，计算每一个char有多少个
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		//定义一个哈希map
		char[] charArray = text.toCharArray();
		//把每一个字符保存在了字符数组里
		for(int i = 0; i < charArray.length; i++)
		{
			if(!map.containsKey(charArray[i]))
			{//如果字符数组中的元素不出现在map当中，把出现的元素加入map中
				map.put(charArray[i], 1);//出现一次添加一个
			}
			else
			{//如果字符数组中的元素出现在map当中，把出现的次数+1
				int value = map.get(charArray[i]);
				//获得之后重新填进去
				map.put(charArray[i], value + 1);
			}//完成了map的输入
		}
		//对哈希map进行遍历
		//计算出每一个字符数组中的元素
		return map;
	}
	
	/**
	 * 使用当前类训练好的huffman编码来对文本进行编码
	 * @return
	 */
	public String encode(String text){
		//TODO
		return null;
	}
	
	/**
	 * 使用指定的huffman编码来对文本进行编码
	 * @return
	 */
	public static String encode(String text, Map<Character, String> code){
		//TODO
		return null;
	}

	/**
	 * 使用当前类中训练好的huffman编码，
	 * 对编码后的文本进行解码
	 * @param text
	 * @return
	 */
	public String decode(String text){
		//TODO
		return null;
	}
	
	public HTNode getTree() {
		return tree;
	}

	/**
	 * 使用预先建立好的huffman树，
	 * 对编码后的文本进行解码
	 * @param text
	 * @return
	 */
	public String decode(String text, HTNode tree){
		//TODO
		return null;
	}
	public static void main(String[] args){
		HuffmanTree htree = new HuffmanTree();
		//首先对字符串中的字符出现次数进行统计
		String data = "In computer science and information theory, "
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
		Map<Character, Integer> chars = HuffmanTree.computeCharCount(data);
		//计算每一个内容出现了多少次
		ArrayList<HTNode> nodes = new ArrayList<>();
		for(Character c : chars.keySet()){
			//.keySet表示把所有的key都取出来变成了一个集合
			//把所有生成的结点放在node链表里面
			HTNode node = new HTNode();
			node.setData(c);
			node.setWeight(chars.get(c));
			//用get方法得到value
			node.setLchild(null);
			node.setRchild(null);
			node.setLeaf(true);
			nodes.add(node);
		}
		HTNode tree = htree.buildTree(nodes);
		//如何根据map来建立树
		Map<Character, String> code = HuffmanTree.getCode(tree);
		for(Character c : code.keySet()){
			System.out.println("字符'"+c+"'的哈夫曼编码："+code.get(c));
		}
		String text = "In computer science and information theory";
		String coded = htree.encode(text);
		System.out.println("字符串：In computer science and information theory");
		System.out.println("被编码为："+coded);
		String oriText = htree.decode(coded);
		System.out.println("编码："+coded);
		System.out.println("被解码为："+oriText);
		System.out.println(oriText.equals(text));
	}
	
	
	
}
