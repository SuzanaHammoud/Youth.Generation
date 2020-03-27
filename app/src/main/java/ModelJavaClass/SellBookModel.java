package ModelJavaClass;

public class SellBookModel {

    // var and for userId and userEmail we gonna retrieve them from the our database
    private String userId,userEmail, bookTitle, boookAuthor, bookCategory, bookDescription, bookPrice, imageUrl;

    // this default constructor is for our firebase
    public SellBookModel() {

    }

    public SellBookModel(String userId, String userEmail, String bookTitle, String boookAuthor, String bookCategory, String bookDescription, String bookPrice, String imageUrl) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.bookTitle = bookTitle;
        this.boookAuthor = boookAuthor;
        this.bookCategory = bookCategory;
        this.bookDescription = bookDescription;
        this.bookPrice = bookPrice;
        this.imageUrl = imageUrl;
    }

    // getters and setters

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBoookAuthor() {
        return boookAuthor;
    }

    public void setBoookAuthor(String boookAuthor) {
        this.boookAuthor = boookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
