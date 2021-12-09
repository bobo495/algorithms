package pers.yibo.algorithms.string;

import java.util.Arrays;

/**
 * MSD基数排序-高位优先的字符串排序
 * <p>
 * 实现一个通用的字符串排序算法（字符串的长度不一定相同），我们应该考虑从左向右遍历 所有字符。
 * <p>
 * LSD is faster than MSD when there is a fixed length. MSD is too slow for small files, and it need huge number of recursive calls on small files.
 *
 * @author yibo
 * @date 2021-12-09 11:55
 **/
public class MostSignificantDigitRadixSort {

    private static final int BITS_PER_BYTE = 8;
    private static final int BIT_PER_INT = 32;
    private static final int RADIX = 256;
    /**
     * 插入排序的截止点
     * <p>
     * 数组长度小于等于16时，使用插入排序
     */
    private static final int CUTOFF = 2;

    private static void sort(String[] arr) {
        String[] aux = new String[arr.length];
        sort(arr, 0, arr.length - 1, 0, aux);
    }

    /**
     * 对比{@link String#charAt(int)}新增了 {@code d = s.length()}的场景，并且返回值为int
     *
     * @param s 字符串
     * @param d 索引
     * @return d为[0, s.length())时，返回对应的字符值；d=s.length()时，返回-1；其他情况抛出异常
     */
    private static int charAt(String s, int d) {
        if (d < 0 || d > s.length()) {
            throw new IllegalArgumentException("d must be between 0 and " + s.length() + ": " + d);
        }
        if (d == s.length()) {
            return -1;
        }
        return s.charAt(d);
    }

    /**
     * 从左往右的键索引计数法
     * <p>
     * 1. 对小子数组使用插入排序提高效率
     * 2. 考虑字符不等长问题，count统计时设置长度+1，并将短字符串排在前面
     *
     * @param arr  字符串
     * @param low  子数组低位索引
     * @param high 子数组高位索引
     * @param d    当前比较的字符串中第d个字符
     * @param aux  临时数组
     */
    private static void sort(String[] arr, int low, int high, int d, String[] aux) {
        // 判断子数组长度，长度小于等于CUTOFF+1则使用插入排序
        if (high <= low + CUTOFF) {
            insertion(arr, low, high, d);
            return;
        }

        // 对子数组的第d个字符进行分组，并统计数量
        int[] count = new int[RADIX + 2];
        // 频率统计：count[0]为空，count[1]为字符串长度为d的数量(参考charAt(),d为arr[i]长度时，返回-1)
        for (int i = low; i <= high; i++) {
            count[charAt(arr[i], d) + 2]++;
        }

        // 统计数量改为有序数组的起始下标，此时，count[0]表示字符串长度为d的起始下标，count[RADIX=1]为空
        for (int r = 0; r < RADIX + 1; r++) {
            count[r + 1] += count[r];
        }

        // 将子数组分发到临时数组aux中
        for (int i = low; i <= high; i++) {
            aux[count[charAt(arr[i], d) + 1]++] = arr[i];
        }

        // 临时数组拷贝回去，aux[0,high-low+1]对应arr[low,high]
        System.arraycopy(aux, 0, arr, low, high + 1 - low);

        // 子数组递归排序
        for (int r = 0; r < RADIX; r++) {
            // r=0时，count[0]表示长度为d的字符串起始下标，count[1]为长度为d的字符串数量，因此对应高位坐标为count[r + 1] - 1
            sort(arr, low + count[r], low + count[r + 1] - 1, d + 1, aux);
        }
    }


    /**
     * 当high-low 小于等于阈值CUTOFF时，采用插入排序
     *
     * @param arr  待比较字符串
     * @param low  数组低位下标
     * @param high 数组高位下标
     * @param d    开始比较的字符索引
     */
    private static void insertion(String[] arr, int low, int high, int d) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && less(arr[j], arr[j - 1], d); j--) {
                exchange(arr, j, j - 1);
            }
        }
    }

    /**
     * 交换数组中的两个元素位置
     *
     * @param arr 数组
     * @param i   下标i
     * @param j   下标j
     */
    private static void exchange(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 从字符索引d开始，判断字符串v是否小于字符串w
     *
     * @param v 字符串
     * @param w 字符串
     * @param d 起始字符索引
     * @return true-v更小，false-相等或w更小
     */
    private static boolean less(String v, String w, int d) {
        // 从d开始逐个字符比较，直至某个字符串比较完毕
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        // 所有字符均相等时，判断长度，v更短则v更小，否则相等或w更小
        return v.length() < w.length();
    }

    private static void sort(int[] arr) {
        int[] aux = new int[arr.length];
        sort(arr, 0, arr.length - 1, 0, aux);
    }

    private static void sort(int[] arr, int low, int high, int d, int[] aux) {
        // 判断子数组长度，长度小于等于CUTOFF+1则使用插入排序
        if (high <= low + CUTOFF) {
            insertion(arr, low, high);
            return;
        }

        // Integer均为32位，因此不需要多留一位
        int[] count = new int[RADIX + 1];
        int mask = 0xFF;
        // 偏移d+1个字节, d=0时，偏移3个字节，即取int左侧8位
        int shift = BIT_PER_INT - BITS_PER_BYTE * d - BITS_PER_BYTE;
        for (int i = low; i <= high; i++) {
            int c = (arr[i] >> shift) & mask;
            count[c + 1]++;
        }

        // 统计数量改为有序数组的起始下标
        for (int r = 0; r < RADIX; r++) {
            count[r + 1] += count[r];
        }


        // 最左边，需要考虑负号，0x80-0xFF是在0x00-0x7F前面的
        // 修改完成后，count[0,0x7F]增加了负数数量,count[0x7F,0xFE]减去了正数数量, count[RADIX]为arr最大索引
        if (d == 0) {
            // 负数数量，首位为1
            int shift1 = count[RADIX] - count[RADIX / 2];
            // 正数数量，首位为0
            int shift2 = count[RADIX / 2];
            // count[1]为0x00开头的数量
            count[RADIX] = shift1 + count[1];
            // 正数索引向后挪{shift1}
            for (int r = 0; r < RADIX / 2; r++) {
                count[r] += shift1;
            }
            // 负数索引向前挪{shift2}
            for (int r = RADIX / 2; r < RADIX; r++) {
                count[r] -= shift2;
            }
        }

        // 将子数组分发到临时数组aux中
        for (int i = low; i <= high; i++) {
            int c = (arr[i] >> shift) & mask;
            aux[count[c]++] = arr[i];
        }

        // 临时数组copy回原数组
        System.arraycopy(aux, 0, arr, low, high + 1 - low);

        if (d == 3) {
            // d=3时比较完毕
            return;
        }

        if (d != 0 && count[0] > 0) {
            // count[0]有值的情况，下面的循环无法表示，因此执行单次递归
            sort(arr, low, low + count[0] - 1, d + 1, aux);
        }

        // 子数组递归排序
        for (int r = 0; r < RADIX; r++) {
            // 过滤为空的分类
            if (count[r + 1] > count[r]) {
                sort(arr, low + count[r], low + count[r + 1] - 1, d + 1, aux);
            }
        }

    }

    /**
     * 当high-low 小于等于阈值CUTOFF时，采用插入排序
     *
     * @param arr  待比较数组
     * @param low  数组低位下标
     * @param high 数组高位下标
     */
    private static void insertion(int[] arr, int low, int high) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && arr[j] < arr[j - 1]; j--) {
                exchange(arr, j, j - 1);
            }
        }
    }

    /**
     * 交换数组中的两个元素位置
     *
     * @param arr 数组
     * @param i   下标i
     * @param j   下标j
     */
    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        String[] testStr = new String[]
                {"paaxw", "nneef", "fsstm", "azedz", "dksat", "pyemk", "pmtzt", "ajffy", "fmdyn", "xrtzi", "mdicc", "afxcd", "btyyi", "bdabx", "dersd", "szmpm", "feeje", "jifdj", "zhhaw", "sxjez", "mzays", "mmisf", "kjbdd", "jniyt", "nbywh", "jzwjj", "chsxi", "dsasd", "xcdbd", "hrppy", "jtdce", "iastf", "yeftn", "dfjwz", "bhcrj", "rwckt", "kxnmc", "reafb", "bpehx", "iwnxp", "sedhz", "kspas", "hjwnn", "wdxfw", "dkhbx", "byths", "dnhij", "phxkb", "bwyjc", "spdkx", "zrstp", "czcxz", "pjywa", "mfhjc", "ampfh", "ijwts", "yaiwh", "weysp", "fkijm", "sykhh", "phrbx", "mcmcf", "hhzrp", "anyxp", "bbtji", "derhi", "zkdjr", "zmyne", "wdebw", "iytcy", "dyrfx", "mwkdt", "cmiej", "mtars", "wjtrp", "ffwxn", "arnba", "tibap", "jxndd", "awjcn", "piibe", "rpzxx", "abdnt", "rstat", "rfnkj", "pkdct", "ywpni", "ytiwr", "sndhm", "dttnk", "ifkpk", "kpsya", "nmxtz", "mmnbb", "ittns", "athtw", "dmipc", "hicss", "nkntm", "antyf", "rfeay", "zzjyn", "cstrx", "nsikw", "ndfkc", "nhcad", "rzhxe", "hcakb", "zfaby", "sfsyx", "pbckb", "mzzew", "ejbfe", "cmiit", "frmms", "czfbd", "fntmm", "isdmw", "naybt", "imjbj", "ekwke", "dibkt", "zbwss", "rhxbh", "dxrda", "ztrtr", "wztjm", "dthmd", "prwyc", "zjrwp", "dcsad", "eneby", "adtcw", "pfcrw", "rizde", "xeecn", "rcxbp", "ernmj", "jxfie", "xtefz", "cjcce", "yftrp", "shsmb", "emnbj", "cycky", "dpzjt", "pdedt", "dwtab", "sbpkn", "mjimp", "dtepf", "khxhe", "dmjnr", "daafr", "sazct", "ejpwc", "micsz", "tkmwd", "rzjpm", "rpadt", "xyjwp", "hwiai", "pbnxe", "mwjsx", "jhcia", "fbrst", "cwedd", "yzbsc", "iyhdd", "imfmz", "ikast", "enpmi", "nkpca", "fpcpm", "jexei", "zxnaj", "tccsk", "jynkk", "zprpe", "hjmbz", "heekz", "cdntk", "xhfys", "bfwyy", "mwair", "bmtrs", "nipss", "yabaj", "tczmt", "sibhd", "yrywh", "ppfhw", "imbay", "pyxir", "xwyer", "ybfpw", "wcass", "aersw", "iesaz", "hbarp", "ynsfw", "ftapr", "sejxe", "twhry", "pmjwt", "rznfa", "nejck", "zdztw", "phssa", "wbbxa", "hertr", "macfr", "wecea", "wbasn", "sjnpd", "ktaxr", "xxkja", "rhztd", "fttni", "ddszp", "bnpam", "eewpm", "ekxpf", "hieaa", "canxm", "ahjam", "wmkeh", "htadm", "ecsap", "yinhy", "yewzx", "kpftk", "taahd", "fszfm", "zrdna", "hmcem", "mzrhn", "ahmwy", "fswst", "btjjr", "rhsze", "cwxwr", "wjzfp", "phdnn", "axeyw", "iewnp", "dycbp", "nweps", "pkkdf", "nprfk", "prmak", "dajzh", "dzpjc", "irtwh", "bdhaa", "ixzbw", "wkwsy", "ksefj", "bnbmb", "abpwx", "rkfhd", "rakth", "hahks", "tksbp", "swazf", "ifcwc", "mhewa", "fatei", "nefsj", "fejfb", "yfbaz", "abkpr", "dijrd", "eixdr", "xxcbz", "ckdwk", "bpeej", "pztee", "fbxby", "ktydw", "eyjbb", "mawji", "erazj", "akmby", "jdazy", "kcjej", "fmhab", "hefbz", "sxapb", "crkaj", "tcerr", "jcskm", "zjjxb", "xbytr", "iwkbe", "ymzbt", "kwpcb", "zzrwn", "zbpkr", "hbhkf", "cmbea", "xjcpc", "zyxmw", "zhmhe", "rtmxk", "tsaef", "nrbxw", "ximit", "wewfh", "cmdcy", "aeybd", "rpeey", "tarpt", "pifre", "sxcii", "zcwkr", "hywzm", "cphkw", "atjnx", "snztf", "twwjp", "kcksx", "zzbhf", "tfhdy", "yszti", "jhcir", "sscaz", "dfhid", "kkdtk", "fkcws", "eymdf", "yxcdd", "mzity", "rnkyj", "kehzm", "jtwzy", "iamhr", "dpskz", "xywtr", "fbdnw", "ybyhw", "mbdza", "ayast", "bisks", "kswbi", "srrpt", "pyipn", "mcmmd", "kbfpj", "bsdma", "yfhee", "cyziw", "nimhy", "fhtzd", "tetyj", "phhmw", "zzzkc", "nbbyt", "jtrwi", "amkwj", "hhbyf", "swdix", "sfrxy", "trfsm", "rtenn", "mkpdh", "twskw", "ctcsx", "zyzyw", "rhjff", "nywne", "krwei", "jmner", "wfnmh", "difsd", "hchfw", "hwfrn", "rmpyt", "bzcbk", "ypfhx", "wijmb", "ytsdk", "myyac", "wxipd", "yzyih", "arsdh", "kajck", "itand", "zhckw", "jpadd", "fitcs", "ikxmb", "ppywd", "cabwm", "bfmrk", "ecdfd", "awhib", "cwkcr", "esxnc", "wpcem", "dcckd", "rccan", "yfchf", "xeict", "nmmxc", "apkfw", "necxe", "sybis", "zcmsz", "nfzaf", "rtscs", "iwifa", "sathk", "mhzwc", "mxzfi", "azefi", "fnmwi", "cihsx", "emdnx", "htahf", "zezzb", "ckkrf", "ynabm", "jkswa", "rmmty", "ayste", "dezkj", "dndzf", "kpjdn", "paapf", "iinew", "picya", "xfakt", "btrdk", "zbctd", "eniac", "mwzbj", "sjbda", "phsdi", "sscsd", "carmk", "kdzfp", "znspt", "eyrwh", "xcimp", "nppty", "rmyzp", "jtapx", "kftzr", "ptnhn", "kkbcr", "zmizs", "tmdzy", "rsjzf", "ccwyk", "rfixi", "nnpfr", "yeten", "hbndw", "esmsb", "bnyee", "zdmkj", "inkpt", "tmdif", "pzreb", "yyaad", "fstxi", "jptnd", "kjzct", "zejca", "epsay", "wwmwe", "ptdfk", "fbami", "ejkwy", "wymkz", "yihcf", "tfiph", "bafyw", "rtssi", "yzbyx", "yaewx", "kcssh", "xjxaz", "bcchy", "zkmfb", "mkdsk", "nwize", "fzejn", "ftsrz", "dsixi", "ezrnw", "hnjcx", "nyziz", "kmjre", "bsidc", "ptred", "tdeef", "isfpm", "tkpfc", "ajnxi", "kfjhp", "itkap", "exays", "trmph", "erjwn", "ibmft", "kiews", "mndah", "sdyrh", "znmmr", "ajars", "dayda", "jkich", "apikr", "dwmxn", "fkdyj", "anzxi", "eirxh", "wxpxn", "bnwwz", "ieptx", "ksicn", "pyiww", "cisip", "yieeh", "yazam", "scren", "kcnsb", "zecjd", "irydc", "zzhec", "tjiew", "mxyjh", "kshbk", "mxnmz", "bswhn", "fwjhr", "bymzi", "hxnsr", "arbdp", "mdaws", "dhpdx", "pbjib", "zwzzp", "piwjb", "mmfbr", "nxtfa", "kykdx", "ecrxw", "thbbk", "izemd", "zxjii", "jfdwd", "rzttt", "twyba", "crsec", "wfahe", "jzchj", "wways", "ezzzn", "cfkhf", "ixbsj", "yidab", "ccjnb", "jxysm", "xttzw", "jiece", "ynhjc", "kwbep", "dtdyf", "tppxe", "wheth", "dnden", "paxpc", "mcxns", "iifxi", "hayim", "mhakn", "sksrx", "ecnxe", "kekkd", "wnstc", "pbenx", "fwtdz", "byzrp", "cwxca", "akpxe", "pckzm", "zdcxk", "wmrdr", "mxmwx", "yxttm", "dteaz", "yhexs", "fffde", "kzjzc", "zsxaa", "zwzwc", "rmejm", "ssdfi", "cwzty", "hkedb", "mtzth", "sjits", "indys", "eipdb", "wthts", "pwykd", "kkyzz", "xjjxy", "inndp", "rszsm", "impyw", "nbwbr", "ammkn", "mpsnz", "nkzad", "pjpst", "ydnwk", "wtysc", "fpwfd", "dtifd", "zsznd", "ecpww", "pkbkf", "rfscc", "nxjmk", "zsiiz", "ypire", "jeazj", "tmyct", "faxxt", "sbrzn", "rmips", "pfbtd", "mfikd", "hnkzh", "bzznc", "rxwdy", "bykpp", "wwfss", "caekb", "jkkya", "eamsp", "ecexh", "ktstj", "rcsiw", "zpzca", "bpbar", "nekir", "ecaey", "wdbrn", "nzpaj", "yfymb", "ijzpd", "pjkci", "rnekk", "cesas", "yrmmn", "cxzmp", "fbcsz", "kzbfd", "beawb", "neaws", "yawhw", "xkfdt", "zmrki", "bypmp", "sbjdh", "ytkiz", "exprr", "tymwy", "xsawd", "wanjz", "dczfd", "hebma", "nwhjz", "wbtmj", "pykma", "mrzws", "azapr", "tseas", "hkekb", "mzcka", "nfywx", "nddbh", "emikd", "rmafb", "hzsxi", "mmdbj", "wftbb", "tmafn", "stnee", "cedkb", "ekdsd", "sxznc", "fncaw", "ebkin", "bsasy", "paszk", "afjfa", "ecwsx", "rchnh", "pehar", "nipmb", "dwpsy", "ebnxj", "hrhab", "idceh", "ckhyj", "xmkzi", "adkpz", "sbxep", "bshwa", "bkrxw", "kmsfd", "rrbse", "cjnsm", "rejfd", "ksmpe", "dszyd", "waktm", "crmia", "ytwxf", "dkzmk", "xpxys", "tywxt", "mfrwe", "tdidz", "btjfn", "efzza", "hkccz", "bbjyh", "dawrn", "kxppc", "kmdda", "bcdbi", "zcabh", "tcznr", "itcpj", "frfis", "pzzes", "fexbp", "pysjd", "fxbid", "ktyeb", "hixwt", "ijsrh", "hrzns", "mzpap", "shkhn", "hwctf", "cennz", "xapbh", "cffax", "efxnp", "ydcyr", "mkymi", "sbnjm", "pmahr", "msbba", "fiyra", "cbbab", "pbeaa", "abaxx", "tbsti", "mepsh", "jijmh", "ewnid", "knyym", "dyhny", "kjyax", "smzhe", "wcxsn", "jmfzy", "fpbxp", "zjxai", "xtdaw", "pixfj", "kkeeb", "ecmat", "ptpkh", "wnaps", "cwtdz", "twpry", "xfmcn", "treca", "nczbc", "irphb", "drjxy", "jthrt", "yjnay", "pypne", "fsdsk", "kekcm", "xfzez", "cnnen", "dawwb", "pfipr", "ezzjx", "jxzdw", "tmpcz", "jjeys", "iwxsh", "kkjyb", "zphmm", "nyase", "jejkm", "rswnf", "bpjbr", "cmcai", "xjbsw", "iapda", "eyrtr", "bzdxk", "arfmh", "xfter", "ajmpj", "inxnn", "fresj", "ndzsd", "rpffr", "mdtsk", "zxbad", "rdxht", "kehsz", "tiamh", "drfhj", "ifdcp", "phjdf", "czfzc", "drbzt", "xtjcp", "wdthe", "bftdk", "dnmbf", "kecnb", "acmap", "xbykm", "abjkt", "cwrmd", "zhwjf", "kxjaa", "sertf", "xbcsp", "yjwfk", "rrczf", "rfyfk", "zenrr", "wzebi", "henbn", "efccs", "pcffx", "ikjjw", "pyikf", "dbjmk", "fjrfy", "stsds", "znbhp", "nzcat", "yteaf", "hrzsp", "snycb", "pidhe", "tyybm", "cnwfe", "zixhj", "sxznd", "anxkm", "xabir", "kdfet", "nzpsj", "eawkt", "ijndd", "shhms", "izmtm", "tjzjs", "dnjjb", "xkdpf", "dmwdz", "xrheh", "ebbfz", "hjeta", "hrsps", "zanty", "srjfj", "yxpxx", "rnfzf", "dmpzb", "ctrej", "trbak", "sktsd", "ixhcz", "xsasy", "jaafk", "nrcwf", "wjfbi", "ccwxb", "sbnzz", "kpben", "exrzc", "jbdwe", "razrt", "iezwb", "iabft", "zyahf", "kdfdr", "txcds", "mharn", "epfdi", "snnnn", "ttewc", "aswpi", "mmmrc", "ntphb", "zhnmk", "aihkr", "sxpcj", "dtswb", "rmape", "nijyw", "pwwpw", "iaanp", "rnzrw", "wknrd", "tpyxt", "wdyts", "kkahp", "dsrzp", "cfmnw", "jzihp", "wahjp", "dzben", "mhcph", "mhpkm", "ainpp", "xhwnh", "kamfb", "zazbp", "xphtf", "txffm", "fizcd", "jamps", "pjxch", "cazad", "wpema", "eyywj", "mzbym", "xhyyi", "pydch", "mnypd", "cwxcf", "cfzts", "jwazn", "esiky", "xawsf", "rwkdt", "hmmxk", "smsmt", "azyir", "wdyhe", "aeisw", "iijpx", "bmbjy", "yzxxy", "dfpmw", "tertw", "haney", "bctwp", "admtn", "nwcwi", "haxmr", "edbaw", "jznew", "myfct", "binft", "bhyic", "rxjzx", "xzfmy", "xjmfz", "ebdpm", "isacm", "aeyai", "eskmz", "zcjss", "fmyry", "fbfhb", "tyhzy", "dakha", "jpejr", "yjmzp", "kbfxb", "fmbih", "djhjw", "jdtrw", "braar", "sbbie", "pfkab", "sykar"};
        sort(testStr);
//        for (String str : testStr) {
//            System.out.println(str);
//        }

        int[] test = new int[]{1, 4, 5, 79, 6, 2, -1, -4, -7, -10, 0, 0, 0, 0x01000000, 0xFEFFFFFF, 0xFFFFFFF0};
        sort(test);
        System.out.println(Arrays.toString(test));

    }
}
