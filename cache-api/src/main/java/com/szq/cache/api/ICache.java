package com.szq.cache.api;

import java.util.Map;

/**
 * @author Ashur
 * @desc 动态规划一般用于求最大值问题
 * 链表题可看情况添加伪节点，可以不用单独判断头节点为空的情况
 * 二维数组题可看情况增加一行一列，不用单独处理特殊情况
 * 所有二叉树的题都要先考虑遍历方式
 * 优先队列(堆)的思路是很朴素的。由于找第 K 大元素，其实就是整个数组排序以后后半部分最小的那个元素。因此，我们可以维护一个有 K 个元素的最小堆：
 * new PriorityQueue<>((x,y)->(y-x));//修改默认参数成大根堆
 * 面试的时候经常碰见诸如获取倒数第k个元素，获取中间位置的元素，
 * 判断链表是否存在环，判断环的长度等和长度与位置有关的问题。这些问题都可以通过灵活运用双指针来解决。
 * 对链表操作记得把最后一位的下一位置为null
 * Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]); 假设传来两个值，v1 与 v2，那么他们的先后顺序以 v1[0] 比 v2[0] 的结果为准，
 * 即：若 v1[0] < v2[0] 则 v1 < v2，若 = 则 =，若 > 则 >
 */
public interface ICache<K, V> extends Map<K, V> {
    /**
     * 设置过期时间
     * （1）如果 key 不存在，则什么都不做。
     * （2）暂时不提供新建 key 指定过期时间的方式，会破坏原来的方法。
     *
     * 会做什么：
     * 类似于 redis
     * （1）惰性删除。
     * 在执行下面的方法时，如果过期则进行删除。
     * {@link ICache#get(Object)} 获取
     * {@link ICache#values()} 获取所有值
     * {@link ICache#entrySet()} 获取所有明细
     *
     * 【数据的不一致性】
     * 调用其他方法，可能得到的不是使用者的预期结果，因为此时的 expire 信息可能没有被及时更新。
     * 比如
     * {@link ICache#isEmpty()} 是否为空
     * {@link ICache#size()} 当前大小
     * 同时会导致以 size() 作为过期条件的问题。
     *
     * 解决方案：考虑添加 refresh 等方法，暂时不做一致性的考虑。
     * 对于实际的使用，我们更关心 K/V 的信息。
     *
     * （2）定时删除
     * 启动一个定时任务。每次随机选择指定大小的 key 进行是否过期判断。
     * 类似于 redis，为了简化，可以考虑设定超时时间，频率与超时时间成反比。
     *
     * 其他拓展性考虑：
     * 后期考虑提供原子性操作，保证事务性。暂时不做考虑。
     * 此处默认使用 TTL 作为比较的基准，暂时不想支持 LastAccessTime 的淘汰策略。会增加复杂度。
     * 如果增加 lastAccessTime 过期，本方法可以不做修改。
     *
     * @param key         key
     * @param timeInMills 毫秒时间
     * @return this
     */
    ICache<K, V> expire(final K key, final long timeInMills);

    /**
     * 在指定的时间过期
     * @param key key
     * @param timeInMills 时间戳
     * @return this
     * @since 0.0.5
     */
    ICache<K, V> expireAt(final K key, final long timeInMills);
}
