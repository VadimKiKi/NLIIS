package Main;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество элементов множеств: ");
        int n = in.nextInt();
        if (n<=0) {
            System.out.println("Введено неверное значение");
            return;
        }
        Vector<Double> mpA = new Vector<>();//меры принадлежности
        Vector<String> elA = new Vector<>();//элементы множества
        System.out.print("Введите меры принадлежностей множества А: ");
        for (int i = 0; i < n; i++) {
            mpA.add(in.nextDouble());
        }
        for (int i = 0; i < n; i++) {
            if (mpA.get(i) < 0 || mpA.get(i) > 1) {
                System.out.println("Введено некорректное значение меры принадлежности");
                return;
            }
        }
        System.out.print("Введите элементы множества А: ");
        for (int i = 0; i < n; i++) {
            elA.add(in.next());
        }
        System.out.print( "Множество А: {");
        for (int i = 0; i < n; i++) {
            System.out.print("("+mpA.get(i) + "/" + elA.get(i) + ")");
        }
        System.out.println("}");
        System.out.println();

        Functoins functoins = new Functoins();

        //альфа срез
        double alpha;
        System.out.print("Введите альфа: ");
        alpha = in.nextDouble();
        functoins.alphaSrez(mpA,elA,n,alpha);
        System.out.println();

        //нормальное или субнормальное множество
        functoins.normality(mpA,n);
        System.out.println();

        //высота
        functoins.height(mpA,n);
        System.out.println();

        //носитель
        functoins.nositel(mpA,elA,n);
        System.out.println();

        //нечеткая мощность
        functoins.nechMosh(mpA,n);
        System.out.println();

        //меры нечеткости
        Vector <Integer> blizh = new Vector<>();

        double Hemming = 0, Euclid = 0;
        int norm = 0;
        for (int i = 0; i < n; i++)
        {
            if (mpA.get(i) >= 0.5){
                norm = 1;
                blizh.add(norm);
            }

            else {
                norm = 0;
                blizh.add(norm);
            }
            Hemming += Math.abs(mpA.get(i) - norm);
            Euclid += Math.pow((mpA.get(i) - norm), 2);
        }
        System.out.print("Ближайшее: {");
        for (int i = 0; i < blizh.size(); i++) {
            System.out.print("("+blizh.get(i)+"/"+elA.get(i)+")");
        }
        System.out.println("}");
        Euclid = Math.sqrt(Euclid);
        System.out.println("Мера нечеткости по Хемминговой метрике: "+ Hemming);
        System.out.println("Мера нечеткости по Евклидовой метрике: " + Euclid);
        System.out.println("Индекс нечеткости по Хемминговой метрике: " + Hemming*(2.0/n));
        System.out.println("Индекс нечеткости по Евклидовой метрике: " + Euclid*(2.0/Math.sqrt(n)));
        System.out.println();

        //множество В
        Vector<Double> mpB = new Vector<>();//меры принадлежности
        Vector<String> elB = new Vector<>();//элементы множества
        System.out.print("Введите меры принадлежностей множества B: ");
        for (int i = 0; i < n; i++) {
            mpB.add(in.nextDouble());
        }
        for (int i = 0; i < n; i++) {
            if (mpB.get(i) < 0 || mpB.get(i) > 1) {
                System.out.println("Введено некорректное значение меры принадлежности");
                return;
            }
        }
        System.out.print("Введите элементы множества B: ");
        for (int i = 0; i < n; i++) {
            elB.add(in.next());
        }
        System.out.println();
        System.out.print("Множество B: {");
        for (int i = 0; i < n; i++) {
            System.out.print("("+mpB.get(i) + "/" + elB.get(i) + ")");
        }
        System.out.println("}");
        System.out.print( "Множество А: {");
        for (int i = 0; i < n; i++) {
            System.out.print("("+mpA.get(i) + "/" + elA.get(i) + ")");
        }
        System.out.println("}");
        System.out.println();

        //пересечение
        functoins.peresechenie(mpA,elA,mpB,elB,n);
        System.out.println();

        //объединение
        functoins.obedinenie(mpA,elA,mpB,elB,n);
        System.out.println();

        //универсальное множество
        Set<String> elUs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            elUs.add(elA.get(i));
            elUs.add(elB.get(i));
        }
        String[] Array = {};
        Array = elUs.toArray(new String[elUs.size()]);
        Iterator<String> iterator = elUs.iterator();

        Vector<String> elU = new Vector<>(Arrays.asList(Array));


        System.out.print( "Универсальное множество U: {");
        for (int i = 0; i < elU.size(); i++) {
            System.out.print(elU.get(i) + " ");
        }
        System.out.println("}");

        //дополнение к А
        Vector<Double> helpmpA = new Vector<>(mpA);
        Vector<String> helpelA=new Vector<>(elA);
        Vector<String> helpelU=new Vector<>(elU);
        System.out.print("Дополнение к А: {");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < elU.size(); j++) {
                if (elA.get(i).equals(elU.get(j))) {
                    double k = 1 - mpA.get(i);
                    System.out.print("(" + k + "/" + elA.get(i) + ")");
                    helpmpA.remove(mpA.get(i));
                    helpelA.remove(elA.get(i));
                    helpelU.remove(elU.get(j));
                }

            }
        }

        for (int i = 0; i < helpelU.size(); i++) {
            System.out.print("(" + 1 + "/" + helpelU.get(i) + ")");
        }
        System.out.println("}");

        //дополнение к В
        Vector<Double> helpmpB = new Vector<>(mpB);
        Vector<String> helpelB=new Vector<>(elB);
        helpelU=new Vector<>(elU);
        System.out.print("Дополнение к B: {");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < elU.size(); j++) {
                if (elB.get(i).equals(elU.get(j))) {
                    double k = 1 - mpB.get(i);
                    System.out.print("(" + k + "/" + elB.get(i) + ")");
                    helpmpB.remove(mpB.get(i));
                    helpelB.remove(elB.get(i));
                    helpelU.remove(elU.get(j));
                }

            }
        }

        for (int i = 0; i < helpelU.size(); i++) {
            System.out.print("(" + 1 + "/" + helpelU.get(i) + ")");
        }
        System.out.println("}");
    }
}
