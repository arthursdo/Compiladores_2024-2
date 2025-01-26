public class Interpretador {
    Double interpretarCodigo(ArvoreSintatica arv) {
        return interpretarCodigo2(arv);
    }

    Double interpretarCodigo2(ArvoreSintatica arv) {
        if (arv instanceof Mult) {
            return interpretarCodigo2(((Mult) arv).arg1) * interpretarCodigo2(((Mult) arv).arg2);
        }

        if (arv instanceof Div) {
            return interpretarCodigo2(((Div) arv).arg1) / interpretarCodigo2(((Div) arv).arg2);
        }

        if (arv instanceof Soma) {
            return interpretarCodigo2(((Soma) arv).arg1) + interpretarCodigo2(((Soma) arv).arg2);
        }

        if (arv instanceof Sub) {
            return interpretarCodigo2(((Sub) arv).arg1) - interpretarCodigo2(((Sub) arv).arg2);
        }

        if (arv instanceof Num) {
            return ((Num) arv).num;
        }

        return 0.0;
    }
}
