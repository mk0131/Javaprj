import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;

public class Hangman2 extends JFrame implements ActionListener {
    String[] words = new String[201];
    int[] checked = new int[201]; // 나왔던 단어 체크하는 배열
    int word_length; // 단어의 길이
    int guessNum; // 맞추는 횟수 (10번만에 맞추는지 8번만에 맞추는지)
    int score;
    int ch = 3;
    int level; // 게임 난이도
    char[] word1 = new char[12]; // 프로그램 안에서 돌아가는 char
    String[] slevel = {"Easy", "Medium", "Hard"}; // 난이도
    String[] word2 = new String[12]; // 화면에 출력할 String
    double wins;
    double looses;
    double winningProsentige;
    String[] btntext = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"};
    JButton[] btn = new JButton[btntext.length];
    JButton begin = new JButton("BEGIN");
    JButton Chance = new JButton("CHANCE");
    JButton easy = new JButton("EASY");
    JButton medium = new JButton("MEDIUM");
    JButton hard = new JButton("HARD");
    JLabel text = new JLabel("Skill level: ", JLabel.LEFT); // 레벨 레이블로 띄우기

    JPanel displayTOP = new JPanel();
    JPanel display1 = new JPanel();
    JPanel display2 = new JPanel();

    Font normalFont = new Font("Arial", Font.BOLD, 16);
    Font warningFont = new Font("Arial", Font.BOLD, 20);

    public Hangman2() {
        for (int j = 0; j < btntext.length; j++) {
            btn[j] = new JButton(btntext[j]);
        }
        setTitle("행맨 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 700);
        setVisible(true);
    }

    public void init() {
        for (int j = 0; j < btntext.length; j++) {
            btn[j].addActionListener(this);
        }

        begin.addActionListener(this);
        Chance.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

        GridLayout aaa = new GridLayout(3, 0); // 전체 panel에 대한 layout 설정
        FlowLayout bbb = new FlowLayout(); // displayTOP에 대한 layout // begin버튼
        FlowLayout ccc = new FlowLayout(); // display2에 대한 layout
        GridLayout ddd = new GridLayout(3, 10, 10, 10); // display1에 대한 layout // 알파벳

        Container root = getContentPane(); // 컨테이너 타입의 객체 root
        root.setLayout(aaa); // grid
        root.setBackground(Color.white);

        displayTOP.add(begin);
        displayTOP.add(Chance);
        displayTOP.setLayout(bbb);
        displayTOP.setBackground(Color.white);

        root.add(displayTOP); // TOP을 컨테이너에 등록
        display1.setLayout(ddd);
        display1.setBackground(Color.white);
        for (int j = 0; j < btntext.length; j++) {
            btn[j].setBackground(Color.orange);
        }
        for (int j = 0; j < btntext.length; j++) {
            display1.add(btn[j]);
        }

        root.add(display1);

        display2.setLayout(ccc);
        display2.setBackground(Color.white);
        display2.add(text);
        display2.add(easy);
        display2.add(medium);
        display2.add(hard);
        root.add(display2);
        setContentPane(root);
        for (int j = 0; j < btntext.length; j++) {
            btn[j].setEnabled(false);
        }

        // begin이 눌리면 그때부터 활성화 (true)
        easy.setEnabled(false);
        medium.setEnabled(true);
        hard.setEnabled(true);

        words[0] = "korea"; // 200개 이상의 단어 구성, 철자 4개 이상 12개 이하
        words[1] = "happy";
        words[2] = "consecutive";
        words[3] = "quality";
        words[4] = "completely";
        words[5] = "complete";
        words[6] = "deliberation";
        words[7] = "formerly";
        words[8] = "conveniently";
        words[9] = " increase";
        words[10] = "decrease";
        words[11] = "consistency";
        words[12] = "enhance";
        words[13] = "establish";
        words[14] = "estimate";
        words[15] = "impressive";
        words[16] = "impression";
        words[17] = "trensfer";
        words[18] = "reduce";
        words[19] = "beware";
        words[20] = "innate";
        words[21] = "restore";
        words[22] = "necessary";
        words[23] = "renovate";
        words[24] = "renovation";
        words[25] = "arise";
        words[26] = "recommend";
        words[27] = "collateral";
        words[28] = "personality";
        words[29] = "appearance";
        words[30] = "certain";
        words[31] = "uncertain";
        words[32] = "disturb";
        words[33] = "speculate";
        words[34] = "delicate";
        words[35] = "speculation";
        words[36] = "scenery";
        words[37] = "consist";
        words[38] = "consistent";
        words[39] = "deficit";
        words[40] = "symptom";
        words[41] = "exceed";
        words[42] = "excessive";
        words[43] = "delegate";
        words[44] = "policy";
        words[45] = "prohibit";
        words[46] = "budget";
        words[47] = "calculate";
        words[48] = "assent";
        words[49] = "exhibit";
        words[50] = "assert";
        words[51] = "preserve";
        words[52] = "significant";
        words[53] = "innovate";
        words[54] = "safety";
        words[55] = "produce";
        words[56] = "courteous";
        words[57] = "refuse";
        words[58] = "expend";
        words[59] = "require";
        words[60] = "contribute";
        words[61] = "innovation";
        words[62] = "competent";
        words[63] = "incompetent";
        words[64] = "insurance";
        words[65] = "mandatory";
        words[66] = "frequently";
        words[67] = "frequency";
        words[68] = "retire";
        words[69] = "pedestrian";
        words[70] = "abuse";
        words[71] = "coordinate";
        words[72] = "instruct";
        words[73] = "amend";
        words[74] = "inquire";
        words[75] = "occupy";
        words[76] = "garner";
        words[77] = "monetary";
        words[78] = "financial";
        words[79] = "alumni";
        words[80] = "expect";
        words[81] = "appliance";
        words[82] = "revenue";
        words[83] = "dependent";
        words[84] = "consent";
        words[85] = "consensus";
        words[86] = "designate";
        words[87] = "prominent";
        words[88] = "frustrate";
        words[89] = "dispose";
        words[90] = "disposal";
        words[91] = "relieve";
        words[92] = "comprehend";
        words[93] = "compare";
        words[94] = "mortgage";
        words[95] = "automotive";
        words[96] = "gratitude";
        words[97] = "reside";
        words[98] = "substantial";
        words[99] = "disorient";
        words[100] = "migrate";
        words[101] = "moon";
        words[102] = "deprive";
        words[103] = "maintain";
        words[104] = "sheer";
        words[105] = "recognize";
        words[106] = "eradicate";
        words[107] = "censure";
        words[108] = "intend";
        words[109] = "intention";
        words[110] = "survey";
        words[111] = "astonish";
        words[112] = "ostrich";
        words[113] = "select";
        words[114] = "feedback";
        words[115] = "emphasis";
        words[116] = "earn";
        words[117] = "access";
        words[118] = "resume";
        words[119] = "instant";
        words[120] = "reimburse";
        words[121] = "eloquent";
        words[122] = "objective";
        words[123] = "susceptible";
        words[124] = "spend";
        words[125] = "mention";
        words[126] = "subscribe";
        words[127] = "alert";
        words[128] = "request";
        words[129] = "realistic";
        words[130] = "indicate";
        words[131] = "discovery";
        words[132] = "waive";
        words[133] = "attire";
        words[134] = "extreme";
        words[135] = "careful";
        words[136] = "prompt";
        words[137] = "devise";
        words[138] = "shrink";
        words[139] = "exaggerate";
        words[140] = "announce";
        words[141] = "clout";
        words[142] = "spacious";
        words[143] = "organize";
        words[144] = "adjourn";
        words[145] = "summary";
        words[146] = "inspire";
        words[147] = "fiscal";
        words[148] = "lecture";
        words[149] = "evident";
        words[150] = "radical";
        words[151] = "solve";
        words[152] = "envision";
        words[153] = "cautious";
        words[154] = "comparable";
        words[155] = "pollute";
        words[156] = "amenity";
        words[157] = "appear";
        words[158] = "thrive";
        words[159] = "invoice";
        words[160] = "donate";
        words[161] = "donation";
        words[162] = "profession";
        words[163] = "analyze";
        words[164] = "analysis";
        words[165] = "grant";
        words[166] = "stipulate";
        words[167] = "incidental";
        words[168] = "equip";
        words[169] = "reject";
        words[170] = "enlarge";
        words[171] = "inherent";
        words[172] = "exclude";
        words[173] = "automate";
        words[174] = "examine";
        words[175] = "instruction";
        words[176] = "disregard";
        words[177] = "ample";
        words[178] = "steady";
        words[179] = "acquisition";
        words[180] = "adhere";
        words[181] = "abate";
        words[182] = "argue";
        words[183] = "negotiate";
        words[184] = "motivate";
        words[185] = "suburb";
        words[186] = "surround";
        words[187] = "decline";
        words[188] = "retain";
        words[189] = "define";
        words[190] = "nimble";
        words[191] = "obtain";
        words[192] = "relevant";
        words[193] = "operate";
        words[194] = "emergency";
        words[195] = "allocate";
        words[196] = "credible";
        words[197] = "menace";
        words[198] = "confer";
        words[199] = "trust";
        words[200] = "violence";

        // 아직 선택되지 않은 단어 (0)으로 초기화
        Arrays.fill(checked, 0);

        for (int i = 0; i < 12; i++) {
            word1[i] = ' '; // character // 프로그램 안에서 맞는지 틀린지
            word2[i] = " "; // string // 화면에 내보낼 때
        }

        /* 필요한 변수들의 초기치 설정 */
        wins = 0;
        looses = 0;
        winningProsentige = 0.0;
    }

    public void paint(Graphics screen) {
        super.paint(screen);
        Graphics2D screen2D = (Graphics2D) screen;
        screen2D.setFont(warningFont);

        screen2D.drawLine(70, 60, 130, 60);
        screen2D.drawLine(70, 60, 70, 80);
        screen2D.drawLine(130, 60, 130, 170);
        screen2D.drawLine(60, 170, 160, 170);

        if (level == 0) {
            switch (guessNum) {
                case 1, 2 -> screen2D.drawOval(60, 80, 20, 20);
                case 3, 4 -> {
                    screen2D.drawOval(60, 80, 20, 20);
                    screen2D.drawLine(70, 100, 70, 130);
                }
                case 5, 6 -> {
                    screen2D.drawOval(60, 80, 20, 20);
                    screen2D.drawLine(70, 100, 70, 130);
                    screen2D.drawLine(50, 110, 90, 110);
                }
                case 7, 8 -> {
                    screen2D.drawOval(60, 80, 20, 20);
                    screen2D.drawLine(70, 100, 70, 130);
                    screen2D.drawLine(50, 110, 90, 110);
                    screen2D.drawLine(70, 130, 55, 150);
                }
                case 9, 10 -> Grpahs(screen2D);
            }
        }

        if (level == 1) {
            switch (guessNum) {
                case 1 -> screen2D.drawOval(60, 80, 20, 20); // 얼굴
                case 2, 3 -> {
                    screen2D.drawOval(60, 80, 20, 20); // 얼굴
                    screen2D.drawLine(70, 100, 70, 130); // 몸통
                }
                case 4, 5 -> {
                    screen2D.drawOval(60, 80, 20, 20); // 얼굴
                    screen2D.drawLine(70, 100, 70, 130); // 몸통
                    screen2D.drawLine(50, 110, 90, 110); // 팔
                }
                case 6, 7 -> {
                    screen2D.drawOval(60, 80, 20, 20); // 얼굴
                    screen2D.drawLine(70, 100, 70, 130); // 몸통
                    screen2D.drawLine(50, 110, 90, 110); // 팔
                    screen2D.drawLine(70, 130, 55, 150); // 왼 다리
                }
                case 8 -> screens(screen2D);
            }
        }

        if (level == 2) {
            switch (guessNum) {
                case 1, 2 -> screen2D.drawOval(60, 80, 20, 20); // 얼굴
                case 3 -> {
                    screen2D.drawOval(60, 80, 20, 20); // 얼굴
                    screen2D.drawLine(70, 100, 70, 130); // 몸통
                }
                case 4 -> {
                    screen2D.drawOval(60, 80, 20, 20); // 얼굴
                    screen2D.drawLine(70, 100, 70, 130); // 몸통
                    screen2D.drawLine(50, 110, 90, 110); // 팔
                }
                case 5 -> {
                    screen2D.drawOval(60, 80, 20, 20); // 얼굴
                    screen2D.drawLine(70, 100, 70, 130); // 몸통
                    screen2D.drawLine(50, 110, 90, 110); // 팔
                    screen2D.drawLine(70, 130, 55, 150); // 왼 다리
                }
                case 6 -> screens(screen2D);
            }
        }

        screen2D.setColor(Color.RED);
        screen2D.drawString(guessNum + " guesses left", 340, 240);
        screen2D.drawString(score + " score", 540, 240);
        screen2D.drawString(Integer.toString(ch), 530, 57);
        screen2D.setFont(normalFont);
        screen2D.setColor(Color.BLACK);
        screen2D.drawString("Current skill level: " + slevel[level], 300, 220);
        screen2D.drawString("Wins ", 220, 200);
        screen2D.drawString(Integer.toString((int) wins), 265, 200);
        screen2D.drawString("Looses", 300, 200);
        screen2D.drawString(Integer.toString((int) looses), 365, 200);
        screen2D.drawString("WinningProsentige", 400, 200);
        screen2D.drawString(winningProsentige + "%", 555, 200);

        // 단어의 길이 4-12로 제한
        int m = 20;
        screen2D.setFont(normalFont);
        screen2D.setColor(Color.BLACK);

        // screen2D.drawImage(img, 100, 100, this);
        if (word_length == 4) {
            for (int i = 0; i < 4; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 5) {
            for (int i = 0; i < 5; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 6) {
            for (int i = 0; i < 6; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 7) {
            for (int i = 0; i < 7; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 8) {
            for (int i = 0; i < 8; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 9) {
            for (int i = 0; i < 9; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 10) {
            for (int i = 0; i < 10; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 11) {
            for (int i = 0; i < 11; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }
        }
        if (word_length == 12) {
            for (int i = 0; i < 12; i++) {

                screen2D.drawString(word2[i], 300 + m, 150);
                m += 20;
            }

        }

        // 답이 맞은 단어에 대한 화면 표시
        // 답이 틀린 단어에 대한 화면 표시
        // 시도한 횟수에 대하여 맞은 단어와 틀린 단어의 수 등을 표시
    }

    private void Grpahs(Graphics2D screen2D) {
        screens(screen2D);
    }

    private void screens(Graphics2D screen2D) {
        screen2D.drawOval(60, 80, 20, 20);
        screen2D.drawLine(70, 100, 70, 130);
        screen2D.drawLine(50, 110, 90, 110);
        screen2D.drawLine(70, 130, 55, 150);
        screen2D.drawLine(70, 130, 85, 150);
    }

    public void wordSelect() {
        double sel_num = Math.random() * 201;// 0~200.xx
        int selection = (int) Math.floor(sel_num); // 0~200
        while (true) { /* 이미 선택된 단어가 다시 선택되는 경우는 배제해야 함 */
            // 나왔던 단어가 또 나오면 안됨
            if (checked[selection] == 0) { // 아직 뽑힌 단어가 아니라면 0
                for (int i = 0; i < 12; i++) {
                    word_length = words[selection].length();
                    word1[i] = words[selection].charAt(i);
                    checked[selection] = 1;
                }

            } else {
                wordSelect();
            }
        }

    }

    public void word_reset() {
        for (int i = 0; i < 12; i++) {
            word2[i] = "_";
        }
        wordSelect();
    }

    public void spell_check(char spell) {
        int check_key = 0;
        for (int i = 0; i < 12; i++) { // 12는 좋은 표현이 아님
            if (word1[i] != ' ') {
                if (word1[i] == spell) {
                    word2[i] = "" + spell;
                    check_key = 1;
                    repaint();
                }
            }
        }

        if (check_key == 0) { // 끝까지 다 찾았는데 check_key가 0이면 특정 알파(a)가 없음
            guessNum--;
            repaint();
        }
        Adjust_display();
        repaint();
    }

    public void Adjust_display() {
        if (word_length == 4) {
            if (!Objects.equals(word2[0], "_") && !Objects.equals(word2[1], "_") && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_")) {
                // 단어 추정 성공
                settings();
                wins++;
                score += guessNum * 10;
                winningProsentige = (wins / (wins + looses)) * 100.0;
                repaint();

            }
        }
        if (word_length == 5) {
            if (!Objects.equals(word2[0], "_") && !Objects.equals(word2[1], "_") && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_") && !Objects.equals(word2[4], "_")) {
                // 단어 추정 성공
                words();

            }
        }
        if (word_length == 6) {
            if (!Objects.equals(word2[0], "_") && !Objects.equals(word2[1], "_") && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_") && !Objects.equals(word2[4], "_")
                    && !Objects.equals(word2[5], "_")) {
                // 단어 추정 성공
                words();

            }
        }
        if (word_length == 7) {
            if (!Objects.equals(word2[0], "_") && !Objects.equals(word2[1], "_") && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_") && !Objects.equals(word2[4], "_")
                    && !Objects.equals(word2[5], "_") && !Objects.equals(word2[6], "_")) {
                // 단어 추정 성공
                words();

            }
        }
        if (word_length == 8) {
            if (!Objects.equals(word2[0], "_") && word2[1] != "_" && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_") && word2[4] != "_"
                    && word2[5] != "_" && !Objects.equals(word2[6], "_") && !Objects.equals(word2[7], "_")) {
                // 단어 추정 성공
                words();

            }
        }
        if (word_length == 9) {
            if (word2[0] != "_" && word2[1] != "_" && word2[2] != "_" && word2[3] != "_" && !Objects.equals(word2[4], "_")
                    && !Objects.equals(word2[5], "_") && word2[6] != "_" && word2[7] != "_" && word2[8] != "_") {
                // 단어 추정 성공
                words();

            }
        }
        if (word_length == 10) {
            if (!Objects.equals(word2[0], "_") && !Objects.equals(word2[1], "_") && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_") && !Objects.equals(word2[4], "_")
                    && !Objects.equals(word2[5], "_") && !Objects.equals(word2[6], "_") && !Objects.equals(word2[7], "_") && !Objects.equals(word2[8], "_") && !Objects.equals(word2[9], "_")) {
                // 단어 추정 성공
                words();

            }
        }
        if (word_length == 11) {
            if (word2[0] != "_" && !Objects.equals(word2[1], "_") && word2[2] != "_" && word2[3] != "_" && !Objects.equals(word2[4], "_")
                    && word2[5] != "_" && !Objects.equals(word2[6], "_") && word2[7] != "_" && word2[8] != "_" && !Objects.equals(word2[9], "_")
                    && !Objects.equals(word2[10], "_")) {
                // 단어 추정 성공
                words();

            }
        }

        if (word_length == 12) {
            if (!Objects.equals(word2[0], "_") && !Objects.equals(word2[1], "_") && !Objects.equals(word2[2], "_") && !Objects.equals(word2[3], "_") && !Objects.equals(word2[4], "_")
                    && !Objects.equals(word2[5], "_") && !Objects.equals(word2[6], "_") && !Objects.equals(word2[7], "_") && !Objects.equals(word2[8], "_") && !Objects.equals(word2[9], "_")
                    && !Objects.equals(word2[10], "_") && !Objects.equals(word2[11], "_")) {
                // 단어 추정 성공
                words();

            }

        }
        if (guessNum == 1) {
            Chance.setEnabled(true);

        }

        if (guessNum <= 0) { // 단어 추정 실패

            // 버튼 눌릴 수 없게 만듬
            for (int j = 0; j < btntext.length; j++) {
                btn[j].setEnabled(false);
            }

            // 정답을 화면에 표시
            for (int i = 0; i < 12; i++) {
                word2[i] = "" + word1[i];
            }

            begin.setEnabled(true);
            // level에 따른 버튼 활성화 작업
            if (level == 0) {
                medium.setEnabled(true);
                hard.setEnabled(true);
            } else if (level == 1) {
                easy.setEnabled(true);
                hard.setEnabled(true);
            } else if (level == 2) {
                easy.setEnabled(true);
                medium.setEnabled(true);
            }
            looses++;
            winningProsentige = (wins / (wins + looses)) * 100.0;
            repaint();

        }

    }

    private void words() {
        settings();
        score += guessNum * 10;
        wins++;
        winningProsentige = (wins / (wins + looses)) * 100.0;
        repaint();
    }

    private void settings() {
        for (int j = 0; j < btntext.length; j++) {
            btn[j].setEnabled(false);
        }
        begin.setEnabled(true);
        if (level == 0) {
            medium.setEnabled(true);
            hard.setEnabled(true);
        } else if (level == 1) {
            easy.setEnabled(true);
            hard.setEnabled(true);
        } else if (level == 2) {
            easy.setEnabled(true);
            medium.setEnabled(true);
        }
    }

    public void correct() {
        if (ch > 0) {
            for (int i = 0; i < 12; i++) {
                if (Objects.equals(word2[i], "_")) {
                    word2[i] = "" + word1[i];
                    break;
                }
            }
            repaint();
        }
        ch--;
    }

    public void actionPerformed(ActionEvent event) {
        String typed = event.getActionCommand(); // 어떤 버튼을 눌렀는지 알려줌
        if (typed.equals("BEGIN")) {
            ch = 3;
            for (int i = 0; i < 12; i++) {
                word1[i] = ' ';
                word2[i] = "_";
            }
            Chance.setEnabled(false);
            easy.setEnabled(false);
            medium.setEnabled(false);
            hard.setEnabled(false);

            if (level == 0) {
                guessNum = 10;
            } else if (level == 1) {
                guessNum = 8;
            } else if (level == 2) {
                guessNum = 6;
            }

            repaint();
            for (int j = 0; j < btntext.length; j++) {
                btn[j].setEnabled(true);
            }

            begin.setEnabled(false);

            word_reset();

        }
        if (typed.equals("CHANCE")) {
            correct();
            if (ch == 0) {
                Chance.setEnabled(false);
            }

        }

        if (typed.equals("A")) {
            btn[0].setEnabled(false);
            spell_check('a');
        }
        if (typed.equals("B")) {
            btn[1].setEnabled(false);
            spell_check('b');
        }
        if (typed.equals("C")) {
            btn[2].setEnabled(false);
            spell_check('c');
        }
        if (typed.equals("D")) {
            btn[3].setEnabled(false);
            spell_check('d');
        }
        if (typed.equals("E")) {
            btn[4].setEnabled(false);
            spell_check('e');
        }
        if (typed.equals("F")) {
            btn[5].setEnabled(false);
            spell_check('f');
        }
        if (typed.equals("G")) {
            btn[6].setEnabled(false);
            spell_check('g');
        }
        if (typed.equals("H")) {
            btn[7].setEnabled(false);
            spell_check('h');
        }
        if (typed.equals("I")) {
            btn[8].setEnabled(false);
            spell_check('i');
        }
        if (typed.equals("J")) {
            btn[9].setEnabled(false);
            spell_check('j');
        }
        if (typed.equals("K")) {
            btn[10].setEnabled(false);
            spell_check('k');
        }
        if (typed.equals("L")) {
            btn[11].setEnabled(false);
            spell_check('l');
        }
        if (typed.equals("M")) {
            btn[12].setEnabled(false);
            spell_check('m');
        }
        if (typed.equals("N")) {
            btn[13].setEnabled(false);
            spell_check('n');
        }
        if (typed.equals("O")) {
            btn[14].setEnabled(false);
            spell_check('o');
        }
        if (typed.equals("P")) {
            btn[15].setEnabled(false);
            spell_check('p');
        }
        if (typed.equals("Q")) {
            btn[16].setEnabled(false);
            spell_check('q');
        }
        if (typed.equals("R")) {
            btn[17].setEnabled(false);
            spell_check('r');
        }
        if (typed.equals("S")) {
            btn[18].setEnabled(false);
            spell_check('s');
        }
        if (typed.equals("T")) {
            btn[19].setEnabled(false);
            spell_check('t');
        }
        if (typed.equals("U")) {
            btn[20].setEnabled(false);
            spell_check('u');
        }
        if (typed.equals("V")) {
            btn[21].setEnabled(false);
            spell_check('v');
        }
        if (typed.equals("W")) {
            btn[22].setEnabled(false);
            spell_check('w');
        }
        if (typed.equals("X")) {
            btn[23].setEnabled(false);
            spell_check('x');
        }
        if (typed.equals("Y")) {
            btn[24].setEnabled(false);
            spell_check('y');
        }
        if (typed.equals("Z")) {
            btn[25].setEnabled(false);
            spell_check('z');
        }

        if (typed.equals("EASY")) {
            easy.setEnabled(false);
            medium.setEnabled(true);
            hard.setEnabled(true);
            level = 0;
            repaint();
        }
        if (typed.equals("MEDIUM")) {
            easy.setEnabled(true);
            medium.setEnabled(false);
            hard.setEnabled(true);
            level = 1;
            repaint();

        }
        if (typed.equals("HARD")) {
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(false);
            level = 2;
            repaint();

        }
    }

    public static void main(String[] args) {
        Hangman2 h = new Hangman2();

        h.init();
    }
}
