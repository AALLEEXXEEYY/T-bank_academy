package tbank.academy.vicilica;

public enum Prints {
    // Конструкция виселицы
    ZERO {
        @Override
        public String toString() {
            return  "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }

    },
    // +палка сверху
    ONE {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    },
    // + голова
    TWO {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "    " + (char) 79 + "    " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    },
    // + тело
    THREE {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "    " + (char) 79 + "    " + (char) 124 + "\n" +
                    "    " + (char) 124 + "    " + (char) 124 + "\n" +
                    "    " + (char) 124 + "    " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    },
    // + рука левая
    FOUR {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "    " + (char) 79 + "    " + (char) 124 + "\n" +
                    "  " + (char) 45 + (char) 45 + (char) 124 + "    " + (char) 124 + "\n" +
                    "    " + (char) 124 + "    " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    },
    // + рука правая
    FIVE {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "    " + (char) 79 + "    " + (char) 124 + "\n" +
                    "  " + (char) 45 + (char) 45 + (char) 124 + (char) 45 + (char) 45 + "  " + (char) 124 + "\n" +
                    "    " + (char) 124 + "    " + (char) 124 + "\n" +
                    "         " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    },
    // + нога левая
    SIX {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "    " + (char) 79 + "    " + (char) 124 + "\n" +
                    "  " + (char) 45 + (char) 45 + (char) 124 + (char) 45 + (char) 45 + "  " + (char) 124 + "\n" +
                    "    " + (char) 124 + "    " + (char) 124 + "\n" +
                    "   " + (char) 47 + "     " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    },
    // + нога правая
    SEVEN {
        @Override
        public String toString() {
            return "     " + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + "\n" +
                    "    " + (char) 79 + "    " + (char) 124 + "\n" +
                    "  " + (char) 45 + (char) 45 + (char) 124 + (char) 45 + (char) 45 + "  " + (char) 124 + "\n" +
                    "    " + (char) 124 + "    " + (char) 124 + "\n" +
                    "   " + (char) 47 + " " + (char) 92 + "   " + (char) 124 + "\n" +
                    (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 95 + (char) 124;
        }
    }
}
