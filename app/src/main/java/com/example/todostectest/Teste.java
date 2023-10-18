package com.example.todostectest;

public class Teste {

    public static class verificaTela {
        private static boolean telaAberta = false;
        private static boolean hasStoppedRunnable = false;

        public static boolean getTelaAberta() {
            return telaAberta;
        }

        public static boolean HasStoppedRunnable() {
            return hasStoppedRunnable;
        }

        public static void setStoppedRunnable(boolean hasStopped) {
            hasStoppedRunnable = hasStopped;
        }

        public static void setTelaAberta(boolean isAberta) {
            telaAberta = isAberta;
        }
    }

    public Teste(boolean telaAberta) {
        verificaTela.telaAberta = telaAberta;
    }

    public void setTelaAberta(boolean bAberto) {
        verificaTela.setTelaAberta(bAberto);
    }
}
