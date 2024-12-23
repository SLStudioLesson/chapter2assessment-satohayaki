package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        //入力されたchoiceが1の場合
                        //displayRecipes()メソッドを呼び出す
                        displayRecipes();
                            
                            
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        //入力されたchoiceが2の場合
                        //addNewRecipes()メソッドを呼び出す
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        //RecipeFileHandlerをインスタンス化
        RecipeFileHandler recipeFileHandler = new RecipeFileHandler();
        //recipeFileHandlerのメソッドreadRecipes()を使用して、recipe.txtの中身をすべて読み取ったArrayListを作成
        ArrayList<String> recipes = recipeFileHandler.readRecipes();
        System.out.println("Recipes:");
        System.out.println("-----------------------------------");
        //拡張for文でrecipesの中身をすべて取り出す
        for(String resi: recipes) {
            //取り出した中身を","で区切った配列menu[]を作成
            String[] menu = resi.split(",");
            
                System.out.print("Recipe name ");
                //配列[0]番目がレシピの名前
                System.out.print(menu[0]);
                System.out.println();
                System.out.print("Main Ingredients: ");
                int j = 0;
                //配列[0]以外は全て材料なので、menu[0]以外をすべて取り出す
                for(int i = menu.length - 1 ; i > 0 ; i--) {
                    j++;
                    System.out.print(menu[j] + " ");
                }
                System.out.println();
                System.out.println("-----------------------------------");
            }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        try{
            //入力のための機能を設定
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter recipe name: ");
        //レシピの名前
        String recipeName = reader.readLine() + ",";
        System.out.println("Enter main ingredients (comma separated): ");
        //材料名
        String ingredients = reader.readLine();

        //RecipeFileHandlerをインスタンス化
        RecipeFileHandler recipeFileHandler = new RecipeFileHandler();
        //addRecipeメソッドを使用し、引数に先ほど入力した文字列を追加
        recipeFileHandler.addRecipe(recipeName, ingredients);

        //処理が完了したら出力する文字列
        System.out.println("Recipe added successfully.");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter search query (e.g., 'name=Tomato&ingredient=Garlic'):");
        String serchWord = reader.readLine();

        RecipeFileHandler recipeFileHandler = new RecipeFileHandler();
        ArrayList<String> resicpes = recipeFileHandler.readRecipes();

        if(serchWord.contains("&")) {
        String[] serchWords = serchWord.split("&");
        String word1 = serchWords[0];
        String word2  = serchWords[1];
        System.out.println("name=" + word1 + "&ingredient=" + word2);
        } else {
            System.out.println("name=" + serchWord);
            System.out.println("Search Results:");
            for(String resi: resicpes) {
                String[] menu = resi.split(",");
                if(menu[0].contains(serchWord)) {
                    for(int i = 0 ; i < menu.length ; i++) {
                        System.out.println(menu[i]);
                    }
                }
            }
        }
    }

}

