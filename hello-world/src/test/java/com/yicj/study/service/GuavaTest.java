package com.yicj.study.service;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.net.HostAndPort;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

/**
 * ClassName: GuavaTest
 * Description: TODO(描述)
 * Date: 2020/8/28 17:11
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class GuavaTest {

    @Test
    void joiner(){
        //连接器
        Joiner joiner1= Joiner.on("=").skipNulls();
        Joiner joiner2 = Joiner.on("&").skipNulls();
        //把集合数组中的元素添加到一起
        String join = joiner1.join(Lists.newArrayList("a", "c", "b"));
        final String join1 = joiner2.join(Lists.newArrayList("a", null, "b"));
        System.out.println(join);
        System.out.println(join1);
    }

    @Test
    void splitter(){
        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
        for (String tmp: splitter.split(" a,  ,b,,")){
            System.out.println("|" + tmp + "|");
        }
    }

    @Test
    void removeFrom(){
        CharMatcher charMatcherInrRange = CharMatcher.inRange('a','b' );
        CharMatcher charMatcherInrAny = CharMatcher.any();
        //删除匹配字符
        System.out.println(charMatcherInrRange.removeFrom("adfasdf"));
        //保留匹配字符
        System.out.println(charMatcherInrRange.retainFrom("adfasdf"));
    }

    @Test
    void asList(){
        List<Integer> list = Ints.asList(1,2,3,4);
        System.out.println(Ints.join(",",1,3,1,4));
    }

    @Test
    void concat(){
        int[] newIntArray = Ints.concat(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println(newIntArray.length);
    }

    @Test
    void maxMin(){
        int[] newIntArray = Ints.concat(new int[]{1, 3, 5}, new int[]{2, 4, 6});

        System.out.println(Ints.max(newIntArray)+"|"+Ints.min(newIntArray));
        //是否包含
        System.out.println(Ints.contains(newIntArray, 6));
    }

    @Test
    void multimap(){
        int[] newIntArray = Ints.concat(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        /**
         * DK提供给我们的Map是一个键，一个值，一对一的，
         * 那么在实际开发中，显然存在一个KEY多个VALUE的情况（比如一个分类下的书本），
         * 我们往往这样表达：Map<k,List<v>>，
         * 我们还得判断KEY是否存在来决定是否new 一个LIST出来，有点麻烦！
         * 更加麻烦的事情还在后头，比如遍历，比如删除，so hard......
         * 友情提示下，guava所有的集合都有create方法，这样的好处在于简单，而且我们不必在重复泛型信息了。
         * get()/keys()/keySet()/values()/entries()/asMap()都是非常有用的返回view collection的方法。
         * Multimap的实现类有：ArrayListMultimap/HashMultimap/LinkedHashMultimap/TreeMultimap/ImmutableMultimap/......
         */
        Multimap<String,String> multimap = ArrayListMultimap.create();
        multimap.put("ryx", "man");
        multimap.put("ryx", "yes");
        List<String> ryx =(List<String>) multimap.get("ryx");
        System.out.println(ryx);
        Preconditions.checkArgument(Ints.contains(newIntArray, 6));
    }

    @Test
    void multiset(){
        /**
         * Multiset
         * Multiset就是无序的，但是可以重复的集合，它就是游离在List/Set之间的&ldquo;灰色地带&rdquo;
         */
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");

        for (String s : multiset) {
            System.out.println(s);
        }
        System.out.println("multiset的值"+multiset);
        System.out.println("multiset的值"+multiset.size());
        System.out.println(multiset.count("a"));
    }

    @Test
    void table(){
        HashBasedTable<String, String, Integer> table = HashBasedTable.create();
        table.put("zhangsan", "语文", 89);
        table.put("zhangsan", "数学", 99);
        table.put("zhangsan", "计算机", 100);
        table.put("lisi", "语文", 79);
        table.put("lisi", "数学", 82);
        table.put("lisi", "计算机", 66);
        Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
        for (Table.Cell<String, String, Integer> cell : cells) {
            System.out.println(cell.getRowKey()+" "+cell.getColumnKey()+" "+cell.getValue());
        }
        Set<String> rowSet = table.rowKeySet();
        System.out.println(rowSet);

        Set<String> columnSet = table.columnKeySet();
        System.out.println(columnSet);

        System.out.println(table.row("zhangsan"));

        HostAndPort hostAndPort = HostAndPort.fromParts("39.106.100.194", 3006);
        System.out.println(hostAndPort);
    }

}