package br.com.dbc.sincronizacao_receita.utils;

import java.io.*;
import java.util.Collection;
import java.util.Map;

/**
 * Classe com alguns metodos utilitarios que podem ser utilizados em varias classes
 */
public class DbcUtil {

    /**
     * Retorna o stack do erro em uma string para log
     * @param aThrowable
     * @return String
     */
    public static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static String formatDecimal(String source){
        if (source == null)
            return null;

        return source.replace(",", ".");
    }

    public static boolean isEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

    /**
     * Verifica se uma String é nula ou vazia
     *
     * @param str
     * @return true se a String é nula ou vazia
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Cria um clone a partir do objeto informado.
     *
     * @param obj - objeto a ser clonado
     * @return <T> T
     */
    public static <T> T clone(T obj) {
        // serialize ArrayList into byte array
        Object clone = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);
            byte buf[] = baos.toByteArray();
            oos.close();

            // deserialize byte array into ArrayList
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            clone = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) clone;
    }
}
