import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
private static Scanner scan = new Scanner(System.in);
private static ArrayList<Expense> Expensives = new ArrayList<>();
private static ArrayList<String> Categories = new ArrayList<>();

	public static void main(String[] args) {
		load();
		Menu();
	}
	
	private static void Menu() {
		safe();
		System.out.println("[1] new Expense");
		System.out.println("[2] see all Expenses");
		System.out.println("[3] delete Expense");
		String Eingabe = scan.next();
		switch(Eingabe) {
		case "1":
			newExpense();
			break;
		case "2":
			seeExpense();
			break;
		case "3":
			deleteExpense();
			break;
		}
		Menu();
	}
	
	private static void newExpense() {
		String category = "";
		for(int i = 0; i < Categories.size(); i++) {
			System.out.println("[" + i + "] " + Categories.get(i));
		}
		System.out.print("Category: ");
		String Eingabe = scan.next();
		
		for(int i = 0; i < Categories.size(); i++) {
			if(Eingabe.equals(String.valueOf(i))) {
				category = Categories.get(i);
			}
		}
		if(category.equals("")) {
			category = Eingabe;
			Categories.add(Eingabe);
		}
		
		double amount = 0;
		System.out.print("amount: ");
		amount = scan.nextDouble();
		
		Expensives.add(new Expense(category,amount));
	}

	private static void seeExpense() {
		double allExpenses = 0;
		for(int i = 0; i < Categories.size(); i++) {
			System.out.println(Categories.get(i));
			for(int j = 0; j < Expensives.size(); j++) {
				if(Expensives.get(j).category().equals(Categories.get(i))) {
					System.out.println(Expensives.get(j).amount());
					allExpenses = allExpenses + Expensives.get(j).amount();
				}
			}
			System.out.println();
		}
		System.out.println("__________________");
		System.out.println("All Together: " + allExpenses);
	}

    private static void deleteExpense() {
    	seeExpense();
    	System.out.println("Which one do you want to delete?");
    	System.out.print("Categorie: ");
    	String Categorie = scan.next();
    	
    	System.out.println("Amount: ");
    	double amount = scan.nextDouble();
    	
    	boolean isFound = false;
    	boolean deleteCategory = true;
    	
    	for(int i = 0; i < Expensives.size(); i++) {
    		String CategorieI = Expensives.get(i).category();
    		double amountI = Expensives.get(i).amount();
    		try {
    			for (int j = 0; j < Expensives.size(); j++) {
    			    Expense e = Expensives.get(j);
    			    if (e.category().equals(Categorie) && e.amount() == amount) {
    			        Expensives.remove(j);
    			        break;
    			    }
    			}        		
    			for(Expense e : Expensives) {
    				if(e.category().equals(CategorieI)) {
    					deleteCategory = false;
    				}
    			}
    			if(deleteCategory) {
    				Categories.remove(CategorieI);
    			}

    		}
    		catch(Exception e){
    			System.out.println("Expense didnt found try again");
    		}    			
    		
    	}
    }

    private static void safe() {
		File Data = new File("src/Memory.txt");
		FileWriter writer = null;
    	try {
			writer = new FileWriter(Data);
		} catch (IOException e) {
			System.out.println("Writer Error");
		}
    	
    	for(Expense e : Expensives) {
    		try {
				writer.write(e.category() + " " + e.amount() +"\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    	try {
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    private static void load() {
		File Data = new File("src/Memory.txt");
		Scanner FileScanner = null;
		try {
			FileScanner = new Scanner(Data).useLocale(Locale.US);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		while(FileScanner.hasNext()) {
			String category = FileScanner.next();
			double amount = FileScanner.nextDouble();
			Expensives.add(new Expense(category,amount));
			boolean CategoryFound = false;
			for(String Cate : Categories) {
				if(category == Cate) {
					CategoryFound = true;
				}
			}
			if(!CategoryFound) {
				Categories.add(category);
			}
		}
        if (FileScanner != null) {
            FileScanner.close();
        }
    }
}
