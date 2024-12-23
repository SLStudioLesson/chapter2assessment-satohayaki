package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        //String型のArrayList、recipesを作成
        ArrayList<String> recipes =new ArrayList<>();
        //ファイルの読み込み
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //ファイルの一行を読みとるが、何行あるか不明なのでnullが出るまで無限ループ
            while ((line = reader.readLine()) != null) {
                //読み取った一行をrecipesに追加
                recipes.add(line);
            }
        } catch (IOException e) {
            //例外、IOExceptionが出た時のメッセージ
            System.out.println("Error reading file:" + e.getMessage());
        }
        //最後にArrayListをreturnで返す
        return recipes;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     //
    public void addRecipe(String recipeName, String ingredients) {
        //ファイルの書き込み処理
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            //引数recipeNameを書き込む
            writer.write(recipeName);
            //引数ingredientsを書き込む
            writer.write(ingredients);
            // ↑の書き込み後に改行する
            writer.newLine();
            
        } catch (IOException e) {
            //IOExceptionが出た時のメッセージ
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
