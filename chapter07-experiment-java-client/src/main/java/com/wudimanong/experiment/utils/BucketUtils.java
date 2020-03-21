package com.wudimanong.experiment.utils;

import java.util.Base64;
import java.util.BitSet;

/**
 * @author jiangqiao
 */
public class BucketUtils {

    /**
     * 每个分层的 bucket 总数
     */
    public static final Integer BUCKET_TOTAL_NUM = 1000;

    /**
     * 将桶编号列表进行Base64压缩
     *
     * @param bucketNos
     * @return
     */
    public static String bucketsToBitStr(Iterable<Integer> bucketNos) {
        BitSet bitSet = new BitSet(BUCKET_TOTAL_NUM);
        bucketNos.forEach(bitSet::set);
        return Base64.getUrlEncoder().encodeToString(bitSet.toByteArray());
    }
}
