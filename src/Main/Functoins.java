package Main;

import java.util.Collections;
import java.util.Vector;

public class Functoins {

    public void alphaSrez(Vector<Double> mp,Vector<String> el, int n, double alpha){
        int check = 0;
        System.out.print("Альфа срез: ");
        for (int i = 0; i < n; i++) {
            if (mp.get(i) >= alpha) {
                System.out.print(el.get(i) + " ");
                check++;
            }
        }
        if (check == 0) {
            System.out.println("Подходящих элементов нет");
        }
        System.out.println();
    }

    public void normality(Vector<Double> mp, int n){
        double check = 0;

        for (int i = 0; i < n; i++) {
            if (mp.get(i) == 1)
                check++;
        }
        if (check == 0)
            System.out.println("Множество является субнормальным");
        else
            System.out.println("Множество является нормальным");
    }

    public void height(Vector<Double> mp,int n){
        double max = -1;
        for (int i = 0; i < n; i++) {
            if (mp.get(i) > max)
                max = mp.get(i);
        }
        System.out.println("Высота: " + max);
    }

    public void nositel(Vector<Double> mp,Vector<String> el, int n){
        System.out.print("Носитель: ");
        for (int i = 0; i < n; i++) {
            if (mp.get(i) > 0)
                System.out.print(el.get(i) + " ");
        }
        System.out.println();
    }

    public  void nechMosh(Vector<Double> mp,int n){
        Vector<Double> helpmp = new Vector<>(mp);
        Vector<Integer> kolvo = new Vector<>();


        double al;

        for (int i = 0; i <n ; i++) {
            int cnt = 0;
            if (helpmp.isEmpty())
                continue;
            al = helpmp.stream().min(Double::compare).get();
            helpmp.removeAll(Collections.singleton(al));
            for (int j = 0; j < n; j++) {
                if (mp.get(j)>=al){
                    cnt++;
                }
            }
            kolvo.add(cnt);
        }
        Collections.sort(kolvo);
        helpmp.clear();
        for (int i = 0; i < n; i++) {
            helpmp.add(mp.get(i));
        }
        Collections.sort(helpmp);
        System.out.print("Нечеткая мощность: {");
        int ch = 0;
        for (int i = 1; i <= n; i++) {
            if (kolvo.get(ch).equals(i)){
                System.out.print("("+ kolvo.get(ch) + "/" + helpmp.get(n - i) + ")");
                ch++;
            }
            else System.out.print("("+ i + "/" + 0 + ")");
        }
        System.out.print("}");
        System.out.println();
    }

    public void peresechenie(Vector<Double> mpA,Vector<String> elA,Vector<Double> mpB,Vector<String> elB, int n){
        System.out.print("Пересечение А и В: {");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++) {
                if (elA.get(i).equals(elB.get(j))) {
                    double min = Math.min(mpA.get(i), mpB.get(j));
                    System.out.print("(" + min + "/" + elA.get(i) + ")");
                }
            }
        }
        System.out.println("}");
    }

    public void obedinenie(Vector<Double> mpA,Vector<String> elA,Vector<Double> mpB,Vector<String> elB, int n){
        Vector<Double> helpmpA = new Vector<>(mpA);
        Vector<Double> helpmpB = new Vector<>(mpB);
        Vector<String> helpelA=new Vector<>(elA);
        Vector<String> helpelB=new Vector<>(elB);
        System.out.print("Объединение А и В: {");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++) {
                if (elA.get(i).equals(elB.get(j))) {
                    double max = Math.max(mpA.get(i), mpB.get(j));
                    System.out.print("(" + max + "/" + elA.get(i) + ")");
                    helpmpA.remove(mpA.get(i));
                    helpmpB.remove(mpB.get(j));
                    helpelA.remove(elA.get(i));
                    helpelB.remove(elB.get(j));
                }

            }
        }
        for (int i = 0; i < helpmpA.size(); i++) {
            System.out.print("(" + helpmpA.get(i) + "/" + helpelA.get(i) + ")");
        }
        for (int i = 0; i < helpmpB.size(); i++) {
            System.out.print("(" + helpmpB.get(i) + "/" + helpelB.get(i) + ")");
        }
        System.out.println("}");
    }
}
