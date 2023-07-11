package com.example.naverwebtoonrenewal;

public class ComicsPojo {

    private ComicsDTO[] webToonList;

    public ComicsDTO[] getItemList() {
        return webToonList;
    }

    public void setItemList(ComicsDTO[] webToonList) {
        this.webToonList = webToonList;
    }
}
