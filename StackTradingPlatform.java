import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {
  private static ArrayList<Stock> stocks = new ArrayList<>();
  private static Portfolio portfolio = new Portfolio();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    // Add stocks
    stocks.add(new Stock("AAPL", 100.0));
    stocks.add(new Stock("GOOG", 500.0));
    stocks.add(new Stock("AMZN", 2000.0));

    // Main loop
    while (true) {
      System.out.println("Stock Trading Platform");
      System.out.println("1. Buy Stock");
      System.out.println("2. Sell Stock");
      System.out.println("3. View Portfolio");
      System.out.println("4. Exit");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          buyStock();
          break;
        case 2:
          sellStock();
          break;
        case 3:
          viewPortfolio();
          break;
        case 4:
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice, please try again.");
          break;
      }
    }
  }

  private static void buyStock() {
    System.out.print("Enter stock symbol: ");
    String symbol = scanner.next();
    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();

    Stock stock = findStockBySymbol(symbol);

    if (stock != null) {
      portfolio.buyStock(stock, quantity);
      System.out.println("Stock bought successfully!");
    } else {
      System.out.println("Stock not found!");
    }
  }

  private static void sellStock() {
    System.out.print("Enter stock symbol: ");
    String symbol = scanner.next();
    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();

    Stock stock = findStockBySymbol(symbol);

    if (stock != null) {
      portfolio.sellStock(stock, quantity);
      System.out.println("Stock sold successfully!");
    } else {
      System.out.println("Stock not found!");
    }
  }

  private static void viewPortfolio() {
    System.out.println("Portfolio Value: " + portfolio.getPortfolioValue());
    System.out.println("Stocks:");
    for (Stock stock : portfolio.getStocks()) {
      System.out.println(stock.getSymbol() + ": " + stock.getQuantity());
    }
  }

  private static Stock findStockBySymbol(String symbol) {
    for (Stock stock : stocks) {
      if (stock.getSymbol().equals(symbol)) {
        return stock;
      }
    }
    return null;
  }
}

class Stock {
  private String symbol;
  private double price;
  private int quantity;

  public Stock(String symbol, double price) {
    this.symbol = symbol;
    this.price = price;
    this.quantity = 0;
  }

  public String getSymbol() {
    return symbol;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}

class Portfolio {
  private ArrayList<Stock> stocks;
  private double portfolioValue;

  public Portfolio() {
    stocks = new ArrayList<>();
    portfolioValue = 0.0;
  }

  public void buyStock(Stock stock, int quantity) {
    Stock portfolioStock = findStockInPortfolio(stock.getSymbol());
    if (portfolioStock == null) {
      portfolioStock = new Stock(stock.getSymbol(), stock.getPrice());
      stocks.add(portfolioStock);
    }
    portfolioStock.setQuantity(portfolioStock.getQuantity() + quantity);
    portfolioValue += stock.getPrice() * quantity;
  }

  public void sellStock(Stock stock, int quantity) {
    Stock portfolioStock = findStockInPortfolio(stock.getSymbol());
    if (portfolioStock != null && portfolioStock.getQuantity() >= quantity) {
      portfolioStock.setQuantity(portfolioStock.getQuantity() - quantity);
      portfolioValue -= stock.getPrice() * quantity;
      if (portfolioStock.getQuantity() == 0) {
        stocks.remove(portfolioStock);
      }
    } else {
      System.out.println("Not enough stock to sell.");
    }
  }

  private Stock findStockInPortfolio(String symbol) {
    for (Stock stock : stocks) {
      if (stock.getSymbol().equals(symbol)) {
        return stock;
      }
    }
    return null;
  }

  public ArrayList<Stock> getStocks() {
    return stocks;
  }

  public double getPortfolioValue() {
    return portfolioValue;
  }
                                         }
