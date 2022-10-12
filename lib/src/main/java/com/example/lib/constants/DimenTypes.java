package com.example.lib.constants;


public enum DimenTypes {

    //适配Android 3.2以上   大部分手机的sw值集中在  300-460之间
    DP_sw__300(300),  // values-sw300
    DP_sw__310(310),
    DP_sw__320(320),
    DP_sw__330(330),
    DP_sw__340(340),
    DP_sw__350(350),
    DP_sw__360(360),
    DP_sw__370(370),
    DP_sw__380(380),
    DP_sw__384(384),
    DP_sw__390(390),
    DP_sw__391(391),
    DP_sw__393(393),
    DP_sw__400(400),
    DP_sw__410(410),
    DP_sw__411(411),
    DP_sw__420(420),
    DP_sw__430(430),
    DP_sw__432(432),
    DP_sw__440(440),
    DP_sw__450(450),
    DP_sw__460(460),
    DP_sw__470(470),
    DP_sw__480(480),
    DP_sw__490(490),
    DP_sw__533(533),
    DP_sw__560(560),
    DP_sw__592(592),
    DP_sw__600(600),
    DP_sw__640(640),
    DP_sw__662(662),
    DP_sw__720(720),
    DP_sw__752(752),
    DP_sw__768(768),
    DP_sw__780(780),
    DP_sw__800(800),
    DP_sw__811(811),
    DP_sw__820(820),
    DP_sw__840(840),
    DP_sw__900(900),
    DP_sw__960(960),
    DP_sw__961(961),
    DP_sw__1024(1024),
    DP_sw__1028(1028),
    DP_sw__1365(1365);
    // 想生成多少自己以此类推


    /**
     * 屏幕最小宽度
     */
    private int swWidthDp;


    DimenTypes(int swWidthDp) {

        this.swWidthDp = swWidthDp;
    }

    public int getSwWidthDp() {
        return swWidthDp;
    }

    public void setSwWidthDp(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }

}
