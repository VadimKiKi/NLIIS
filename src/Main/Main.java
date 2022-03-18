package Main;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

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
        double Hemming = 0, Euclid = 0;
        int norm = 0;
        for (int i = 0; i < n; i++)
        {
            if (mpA.get(i) >= 0.5)
                norm = 1;
            else norm = 0;
            Hemming += Math.abs(mpA.get(i) - norm);
            Euclid += Math.pow((mpA.get(i) - norm), 2);
        }
        Euclid = Math.sqrt(Euclid);
        System.out.println("Мера нечеткости по Хемминговой метрике: "+ Hemming);
        System.out.println("Мера нечеткости по Евклидовой метрике: " + Euclid);
        System.out.println("Индекс нечеткости по Хемминговой метрике: " + Hemming*(double) (2/n));
        System.out.println("Индекс нечеткости по Евклидовой метрике: " + Euclid*(2/Math.sqrt(n)));
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
    }
}
