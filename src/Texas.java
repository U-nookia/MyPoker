import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lenovo on 2016/10/9.
 */
public class Texas {
    private static final String SPADES = "spades";
    private static final String CLUBS = "clubs";
    private static final String HEARTS = "hearts";
    private static final String DIAMONDS = "diamonds";
    String[] colors = {SPADES,CLUBS,HEARTS,DIAMONDS};
    List<Poker> pokers = new ArrayList<>();
    List<Poker> publicPokers = new ArrayList<>();
    List<Poker> myPokers = new ArrayList<>();
    List<Poker> opponentsPokers = new ArrayList<>();
    int me = 1,oppo = 1;

    public static void main(String[] args) {
        Texas texas = new Texas();
        texas.initPokers();
        texas.shufflePokers();
        //texas.showPokers();
        texas.dealPublicPokers();
        texas.dealCardsInHand();
        texas.compare();
    }

    /*
    初始化一副牌，未洗
     */
    private void initPokers(){
        for (int i = 0;i<colors.length;i++){
            for (int j = 0;j<13;j++){
                Poker poker = new Poker();
                poker.setColor(colors[i]);
                poker.setNumber(j+2);
                setPoint(j+2,poker);
                pokers.add(poker);
            }
        }
    }

    /*
    设置牌点
     */
    private void setPoint(int n,Poker poker) {
        switch (n){
            case 14:
                poker.setPoint("A");
                break;
            case 11:
                poker.setPoint("J");
                break;
            case 12:
                poker.setPoint("Q");
                break;
            case 13:
                poker.setPoint("K");
                break;
            default:
                poker.setPoint(""+n);
                break;
        }
    }

    /*
    洗牌
     */
    private void shufflePokers(){
        //System.out.println((int)(Math.random()*53)+"");
        for (int i = 51;i>=1;i--){
            Poker poker = new Poker();
            Poker poker_last = pokers.get((int)(Math.random()*i));
            Poker poker_next = pokers.get(i);
            poker.equal(poker_last);
            poker_last.equal(poker_next);
            poker_next.equal(poker);
            pokers.set(i,poker_next);
        }
    }

    /*
    显示目前的所有牌
     */
    private void showPokers(){
        for (Poker poker:pokers){
            System.out.println(poker.getColor()+poker.getPoint());
        }
    }

    /*
    发公共牌
     */
    private void dealPublicPokers(){
        for (int i = 0;i<5;i++){
            int n = (int)(Math.random()*(pokers.size()));
            publicPokers.add(pokers.get(n));
            pokers.remove(n);
        }
        System.out.println("公共牌为：");
        for (Poker poker:publicPokers) System.out.println(poker.getColor()+poker.getPoint()+" ");
    }

    /*
    发底牌
     */
    private void dealCardsInHand(){
        for (int i = 0;i<2;i++){
            int n = (int)(Math.random()*(pokers.size()));
            myPokers.add(pokers.get(n));
            pokers.remove(n);
        }
        for (int i = 0;i<2;i++){
            int n = (int)(Math.random()*(pokers.size()));
            opponentsPokers.add(pokers.get(n));
            pokers.remove(n);
        }
        System.out.println("我的底牌为：");
        for (Poker poker:myPokers) System.out.println(poker.getColor()+poker.getPoint()+" ");
        System.out.println("对手底牌为：");
        for (Poker poker:opponentsPokers) System.out.println(poker.getColor()+poker.getPoint()+" ");
        System.out.println("  ");
    }

    /*
    比较双方的牌大小
     */
    private void compare(){
        for (int i = 0;i<5;i++){
            myPokers.add(publicPokers.get(i));
            opponentsPokers.add(publicPokers.get(i));
        }

        Collections.sort(myPokers, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                if(o1.getNumber() > o2.getNumber()){
                    return 1;
                }
                if(o1.getNumber() == o2.getNumber()){
                    return 0;
                }
                return -1;
            }
        });
        Collections.sort(opponentsPokers, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                if(o1.getNumber() > o2.getNumber()){
                    return 1;
                }
                if(o1.getNumber() == o2.getNumber()){
                    return 0;
                }
                return -1;
            }
        });
        me = judge(myPokers);
        String me_result = result(me);
        System.out.println("你的牌为："+me_result);
        oppo = judge(opponentsPokers);
        String oppo_result = result(oppo);
        System.out.println("对手的牌为："+oppo_result);

        if (me>oppo) System.out.println("你赢了");
        else if (me<oppo) System.out.println("对方赢了");
        else if (me == oppo){
            System.out.println("牌型相同，开始依次比较最大牌:");
            for (int i=6;i>=0;i--){
                if (myPokers.get(i).getNumber()==opponentsPokers.get(i).getNumber()){
                    continue;
                }else if (myPokers.get(i).getNumber()>opponentsPokers.get(i).getNumber()){
                    System.out.println("你赢了");
                    break;
                }else {
                    System.out.println("对方赢了");
                    break;
                }
            }
        }
    }

    private String result(int result) {
        String s=null;
        switch (result){
            case 1:
                s = "高牌";
                break;
            case 2:
                s = "一对";
                break;
            case 3:
                s = "两对";
                break;
            case 4:
                s = "三条";
                break;
            case 5:
                s = "顺子";
                break;
            case 6:
                s = "同花";
                break;
            case 7:
                s = "葫芦";
                break;
            case 8:
                s = "四条";
                break;
            case 9:
                s = "同花顺";
                break;
            case 10:
                s = "皇家同花顺";
                break;
        }
        return s;
    }

    /*
    判断牌型
     */
    private int judge(List<Poker> pokers1) {
        int n=1;

        for (Poker mpoker:pokers1) System.out.println(mpoker.getColor()+mpoker.getPoint()+" ");

        for (int i = 2;i>=0;i--){
            if ((pokers1.get(i).getColor().equals(pokers1.get(i+1).getColor()))&&(pokers1.get(i).getColor().equals(pokers1.get(i+2).getColor()))&&
                    (pokers1.get(i).getColor().equals(pokers1.get(i+3).getColor()))&&(pokers1.get(i).getColor().equals(pokers1.get(i+4).getColor()))){
                //同花
                if ((pokers1.get(i).getNumber()+1==pokers1.get(i+1).getNumber())&&(pokers1.get(i+1).getNumber()+1==pokers1.get(i+2).getNumber())&&
                        (pokers1.get(i+2).getNumber()+1==pokers1.get(i+3).getNumber())&&(pokers1.get(i+3).getNumber()+1==pokers1.get(i+4).getNumber())){
                    //同花且有顺序
                    if (pokers1.get(i+4).getNumber()==14)  {
                        //同花且有顺序且最大为A
                        n=10;
                        break;
                    }
                    n=9;
                    break;
                }else {
                    //同花但不按顺序
                    int m = judgeNum(pokers1.get(i).getNumber(),pokers1.get(i+1).getNumber(),pokers1.get(i+2).getNumber(),pokers1.get(i+3).getNumber(),pokers1.get(i+4).getNumber());
                    if (m>6){
                        if (n<m){
                            n=m;
                        }
                    }else if (n<6){
                        n=6;
                    }
                    continue;
                }
            }else if ((pokers1.get(i).getNumber()+1==pokers1.get(i+1).getNumber())&&(pokers1.get(i+1).getNumber()+1==pokers1.get(i+2).getNumber())&&
                    (pokers1.get(i+2).getNumber()+1==pokers1.get(i+3).getNumber())&&(pokers1.get(i+3).getNumber()+1==pokers1.get(i+4).getNumber())){
                //不同花但顺序
                if (n<5) n=5;
                continue;
            }else{
                //不同花且不顺序
                int n1 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber());
                int n2 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(5).getNumber());
                int n3 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(6).getNumber());
                int n4 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber());
                int n5 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(4).getNumber(),pokers1.get(6).getNumber());
                int n6 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n7 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber());
                int n8 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(6).getNumber());
                int n9 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(3).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n10 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(1).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n11 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber());
                int n12 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(6).getNumber());
                int n13 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n14 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(2).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n15 = judgeNum(pokers1.get(0).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n16 = judgeNum(pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber());
                int n17 = judgeNum(pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(6).getNumber());
                int n18 = judgeNum(pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n19 = judgeNum(pokers1.get(1).getNumber(),pokers1.get(2).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n20 = judgeNum(pokers1.get(1).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                int n21 = judgeNum(pokers1.get(2).getNumber(),pokers1.get(3).getNumber(),pokers1.get(4).getNumber(),pokers1.get(5).getNumber(),pokers1.get(6).getNumber());
                List<Integer> lists = new ArrayList<>();
                lists.add(n1);
                lists.add(n2);
                lists.add(n3);
                lists.add(n4);
                lists.add(n5);
                lists.add(n6);
                lists.add(n7);
                lists.add(n8);
                lists.add(n9);
                lists.add(n10);
                lists.add(n11);
                lists.add(n12);
                lists.add(n13);
                lists.add(n14);
                lists.add(n15);
                lists.add(n16);
                lists.add(n17);
                lists.add(n18);
                lists.add(n19);
                lists.add(n20);
                lists.add(n21);
                Collections.sort(lists);
                if (n<lists.get(20)) n = lists.get(20);
            }
        }

        return n;
    }

    private int judgeNum(int a,int b,int c,int d,int e){
        if ((a==b&&a==c&&a==d)||(a==b&&a==c&&a==e)||(a==b&&a==e&&a==d)||(a==e&&a==c&&a==d)||(c==b&&b==e&&b==d)){
            return 8;
        }else if ((a==b&&a==c&&a!=d&&d==e)||(a==b&&a==d&&a!=c&&c==e)||(a==b&&a==e&&a!=c&&d==c)||
                (a==d&&a==c&&a!=b&&b==e)||(a==e&&a==c&&a!=d&&d==b)||(a==d&&a==e&&a!=b&&b==c)||(c==b&&d==c&&c!=a&&a==e)||
                (c==b&&e==c&&c!=a&&a==d)||(e==d&&d==c&&a!=d&&a==b)||(d==b&&d==e&&d!=a&&a==c)){
            return 7;
        }else if ((a==b&&a==c)||(a==b&&a==d)||(a==b&&a==e)||
                (a==d&&a==c)||(a==e&&a==c)||(a==d&&a==e)||(c==b&&d==c)||
                (c==b&&e==c)||(e==d&&d==c)||(d==b&&d==e)){
            return 4;
        }else if (a==b&&c==d||a==c&&b==d||a==d&&b==c||a==b&&c==e||a==c&&b==e||a==e&&b==c||a==b&&d==e||a==d&&b==e||a==c&&b==d||a==c&&d==e||
                a==d&&c==e||a==e&&c==d||b==c&&d==e||b==d&&c==e||b==e&&c==d){
            return 3;
        }else if (a==b||a==c||a==d||a==e||b==c||b==d||b==e||c==d||c==e||d==e){
            return 2;
        }else {
            return 1;
        }
    }
}
