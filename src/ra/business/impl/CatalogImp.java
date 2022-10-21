package ra.business.impl;

import ra.business.design.Icrud;
import ra.business.entity.Catalog;
import ra.business.notify.Validate;
import ra.data.DataURL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogImp implements Icrud<Catalog, Integer> {
    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> catalogList = readFromfile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        catalogList.add(catalog);
        boolean result = writeToFile(catalogList);
        return result;
    }

    @Override
    public boolean update(Catalog catalog) {
        List<Catalog> catalogList = readFromfile();
        boolean returnData = false;
        for (int i = 0; i < catalogList.size(); i++) {
            if (catalogList.get(i).getCatalogId() == catalog.getCatalogId()) {
                catalogList.set(i, catalog);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(catalogList);
        return result;
    }

    @Override
    public boolean delete(Integer integer) {
        List<Catalog> catalogList = readFromfile();
        boolean returnData = false;
        for (int i = 0; i < catalogList.size(); i++) {
            if (catalogList.get(i).getCatalogId() == integer) {
                catalogList.get(i).setCatalogStatus(!catalogList.get(i).isCatalogStatus());
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(catalogList);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public List<Catalog> readFromfile() {
        List<Catalog> catalogList = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_CATALOG_FILE);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            catalogList = (List<Catalog>) ois.readObject();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return catalogList;
    }

    @Override
    public boolean writeToFile(List<Catalog> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_CATALOG_FILE);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception ex1) {
            returnData = false;
            ex1.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return returnData;
    }

    @Override
    public Catalog inputData(Scanner sc) {
        List<Catalog> catalogList = readFromfile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        Catalog catalogNew = new Catalog();
        if (catalogList.size() == 0) {
            catalogNew.setCatalogId(1);
        } else {
            int max = 0;
            for (Catalog cat : catalogList) {
                if (max < cat.getCatalogId()) {
                    max = cat.getCatalogId();
                }
            }
            catalogNew.setCatalogId(max + 1);
        }
        System.out.println("Nhập vào tên danh mục");
        do {
            String catalogName = sc.nextLine();
            if (catalogName.trim().length() != 0 && catalogName.trim() != "") {
                if (catalogName.trim().length() >= 6 && catalogName.trim().length() <= 50) {
                    boolean check = true;
                    for (Catalog cat : catalogList) {
                        if (cat.getCatalogName().equals(catalogName)) {
                            check = false;
                        }
                    }
                    if (check) {
                        catalogNew.setCatalogName(catalogName);
                        break;
                    } else {
                        System.err.println("Tên đăng nhập đã bị trùng, vui lòng nhập tên khác");
                    }
                } else {
                    System.err.println("Vui lòng nhap tên danh mục từ 6 - 50 ký tự");
                }
            } else {
                System.err.println("Tên danh mục không được để trống");
            }
        } while (true);
        System.out.println("Nhập vào trạng thái danh mục");
        System.out.println("1. Hoạt động");
        System.out.println("2. Không hoạt động");
        System.out.println("Lựa chọn của bạn là");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                catalogNew.setCatalogStatus(true);
            } else if (choice == 2) {
                catalogNew.setCatalogStatus(false);
            } else {
                System.err.println("Vui lòng chọn 1 hoặc 2");
            }
        } catch (NumberFormatException ex1) {
            System.err.println("Vui lòng nhập vào một số nguyên");
        }
        for (Catalog cat:catalogList) {
            if (cat.getCatalog()==null){
                displayListCatalog(cat,catalogList,0);
            }
        }
        System.out.println("lựa chọn danh mục");
        try {
            int choice2 = Integer.parseInt(sc.nextLine());
            if (choice2>0&&choice2<catalogList.size()){
                catalogNew.setCatalog(catalogList.get(choice2-1));
            } else {
                catalogNew.setCatalog(null);
            }
        } catch (NumberFormatException ex2){
            System.err.println("Vui lòng nhập vào một số nguyên");
        }
        return catalogNew;
    }

    @Override
    public void displayData(Catalog catalog) {
        String status = "Không hoạt động";
        if (catalog.isCatalogStatus()){
            status = "Hoạt động";
        }
        System.out.printf("");
    }
    @Override
    public List<Catalog> findAll() {
        return readFromfile();
    }

    @Override
    public Catalog findById(Integer id) {
        List<Catalog> catalogList = readFromfile();
        for (Catalog cat : catalogList) {
            if (cat.getCatalogId() == id) {
                return cat;
            }
        }
       return null;
    }

    public static void displayListCatalog(Catalog root, List<Catalog> list, int cnt) {
        for (int i = 0; i < cnt; i++) {
            System.out.println("\t");
        }
        System.out.printf("%d. %s\n", root.getCatalogId(), root.getCatalogName());
        List<Catalog> listchild = new ArrayList<>();
        for (Catalog cat : list) {
            if (cat.getCatalog() != null && cat.getCatalogId() == root.getCatalogId()) {
                listchild.add(cat);
            }
        }
        if (listchild.size() != 0) {
            cnt++;
        }
        for (Catalog cat : listchild) {
            displayListCatalog(cat, list, cnt);
        }
    }
}
