package com.example.todostectest;

public class ManipulaTela {

    public static class verificaTela {
        private static boolean telaAberta = false;
        private static boolean hasStoppedRunnable = false;
        private static boolean isOpen = false;
        private static boolean internetError = false;
        private static boolean isOpenInternetError = false;
        private static boolean verificaCadastro = false;

        public static boolean isVerificaCadastro() {
            return verificaCadastro;
        }

        public static void setVerificaCadastro(boolean verificaCadastro) {
            verificaTela.verificaCadastro = verificaCadastro;
        }

        public static boolean isIsOpenInternetError() {
            return isOpenInternetError;
        }

        public static void setIsOpenInternetError(boolean isOpenInternetError) {
            verificaTela.isOpenInternetError = isOpenInternetError;
        }

        public static boolean isInternetError() {
            return internetError;
        }

        public static void setInternetError(boolean internetError) {
            verificaTela.internetError = internetError;
        }

        public static boolean getIsOpen() {
            return isOpen;
        }

        public static void setIsOpen(boolean isOpen) {
            verificaTela.isOpen = isOpen;
        }

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

    public ManipulaTela(boolean telaAberta) {
        verificaTela.telaAberta = telaAberta;
    }

    public void setTelaAberta(boolean bAberto) {
        verificaTela.setTelaAberta(bAberto);
    }
}
