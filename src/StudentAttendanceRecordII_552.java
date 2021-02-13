public class StudentAttendanceRecordII_552 {
    //Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
    //
    //A student attendance record is a string that only contains the following three characters:
    //
    //'A' : Absent.
    //'L' : Late.
    //'P' : Present.
    //A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
    //
    //Example 1:
    //Input: n = 2
    //Output: 8
    //Explanation:
    //There are 8 records with length 2 will be regarded as rewardable:
    //"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
    //Only "AA" won't be regarded as rewardable owing to more than one absent times.
    //Note: The value of n won't exceed 100,000.


    //动态规划 状态框架

    //审题与思考
    //看示例像是排列组合的题目
    //求总共多少种，而不需要列出有哪些
    //最多 1 个 A、2 个 连续的 L，P 随意来
    //尝试人肉傻递归，感受一下示例的 n = 2
    //你会发现 n 就好像树的每一层
    //每下探一层，就是在后面加上一个新字符
    //顺着 "加字符" 的思路接着顺，寻找我们要思考的 哪几个方面
    //关注 A ，只关心整个字符串里 有 或 没有
    //若有，则下一层不能再加 A 了
    //若无，下一层可以加 A
    //关注 L ，只关心最后的两个字符是不是 LL
    //因为前面的要是有 LLL ，那早就应该剪枝了，不会再到这一层
    //如果前面有 LL ，但又被其他字符间隔，那也不影响当前的关注
    //哪几个方面 = 每层需要了解的维度 = 状态维度
    //总结状态维度
    //现在是哪一层 i，范围 [1, n]
    //技巧：按 [0, n] 可更直观，省去 n - 1 的表示
    //有没有 A，范围 [0, 1]
    //末尾有几个连续的 L，范围 [0, 2]
    //得出状态 long[][][] dp = new long[n + 1][2][3];
    //为何 long 而不是 int？省点 mod 的吧，空间换时间
    //再回顾感受一下：总共 2 * 3 = 62∗3=6 种情况 都是有效的
    //dp[i][0][0]：无 A，末尾无 0 个 L，简记 0A0L，如 ..PLP..PLLP..LP
    //dp[i][0][1]：0A1L，如 ..PLP..PLLP..PL
    //dp[i][0][2]：0A2L，如 ..PLP..PLLP..PLL
    //dp[i][1][0]：1A0L，如 ..PLP..A..PLLP..P / ..PLP..PLLP..A
    //dp[i][1][1]：1A1L，如 ..PLP..A..PLLP..PL
    //dp[i][1][2]：1A2L，如 ..PLP..A..PLLP..PLL
    //无效的状态去哪了？
    //上述六中情况之外的，都是无效的，我们不放入 dp 三维数组里，就相当于排除掉了
    //比如 dp[..][2][..] ，两个 A，无效，dp 没这个维度容量，排除
    //比如 dp[..][..][3] ，末尾 LLL，无效，dp 没这个维度容量，排除
    //搭建代码框架
    //
    public int checkRecord(int n) {
        int _MOD = 1000000007;
        long[][][] dp = new long[n + 1][2][3]; // i, A:{0:non, 1:contains}, L:{0,1,2}
        // TODO 初始状态，第 1 层
        // dp[1][..][..]
        for (int i = 2; i <= n; i++) {
            // +P

            // +L

            // +A

        }

        // 返回第 n 层的六种情况
        return (int) ((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % _MOD);
    }
    //初始状态
    //
    //dp[1][1][0] = 1; // A，第一层，有A，末尾没有L，1A0L
    //dp[1][0][1] = 1; // L，第一层，无A，末尾1个L，0A1L
    //dp[1][0][0] = 1; // P，第一层，无A，末尾没有L，0A0L
    //状态转移方程
    //javajava
    //
    //// 当前这末尾+P
    //// 无A末尾无L 可来自上一层  无A末尾无L   +     无A末尾1L   +    无A末尾2L
    //// 0A0L     =                  0A0L      +       0A1L      +       0A2L
    //dp[i][0][0] =          (dp[i - 1][0][0]  + dp[i - 1][0][1] + dp[i - 1][0][2]) % _MOD;
    //// 同理，接着推
    //// 1A0L     =       1A0L       +       1A1L      +       1A2L
    //dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % _MOD;
    //
    //// +L
    //// 0A1L = 0A0L
    //dp[i][0][1] = dp[i - 1][0][0];
    //// 0A2L = 0A1L
    //dp[i][0][2] = dp[i - 1][0][1];
    //// 1A1L = 1A0L
    //dp[i][1][1] = dp[i - 1][1][0];
    //// 1A2L = 1A1L
    //dp[i][1][2] = dp[i - 1][1][1];

    //// +A
    //// 此处的 dp[i][1][0] 在上面 +P 时已经开始出现，故此处应 +=
    //// 1A0L = 0A0L + 0A1L + 0A2L
    //dp[i][1][0] += (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % _MOD;
    //压缩状态空间
    //使用六个变量，直接枚举六种状态
    //直观的在每一层有 6 个 temp 临时变量辅助滚动
    public int checkRecord2(int n) {
        int _MOD = 1000000007;
        long dp00, dp01, dp02, dp10, dp11, dp12;
        dp00 = dp01 = dp10 = 1;
        dp11 = dp02 = dp12 = 0;
        for (int i = 2; i <= n; i++) {
            long t00 = dp00, t01 = dp01, t02 = dp02, t10 = dp10, t11 = dp11, t12 = dp12;
            // +P
            // 0A0L = 0A0L + 0A1L + 0A2L
            dp00 = (t00 + t01 + t02) % _MOD;
            // 1A0L = 1A0L + 1A1L + 1A2L
            dp10 = (t10 + t11 + t12) % _MOD;
            // +L
            // 0A1L = 0A0L
            dp01 = t00;
            // 0A2L = 0A1L
            dp02 = t01;
            // 1A1L = 1A0L
            dp11 = t10;
            // 1A2L = 1A1L
            dp12 = t11;
            // +A
            // 1A0L = 0A0L + 0A1L + 0A2L
            dp10 += (t00 + t01 + t02) % _MOD;
        }
        return (int) ((dp00 + dp01 + dp02 + dp10 + dp11 + dp12) % _MOD);
    }

    //到你了，试着默写一次吧？
    //可以从这里开始
    public int checkRecord3(int n) {
        int _MOD = 1000000007;
        long[][][] dp = new long[n + 1][2][3]; // i, A:{0:non, 1:contains}, L:{0,1,2}
        // TODO 初始状态，第 1 层
        // dp[1][..][..]
        for (int i = 2; i <= n; i++) {
            // +P

            // +L

            // +A

        }

        // 返回第 n 层的六种情况
        return (int) ((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % _MOD);
    }

}
