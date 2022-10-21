package ra.business.impl;

import ra.business.design.Icrud;
import ra.business.entity.Catalog;
import ra.business.entity.Color;
import ra.data.DataURL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColorImp implements Icrud<Color, Integer> {

    @Override
    public boolean create(Color color) {
        List<Color> colorList = readFromfile();
        if (colorList == null) {
            colorList = new ArrayList<>();
        }
        colorList.add(color);
        boolean result = writeToFile(colorList);
        return result;
    }

    @Override
    public boolean update(Color color) {
        List<Color> colorList = readFromfile();
        boolean returnData = false;
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getColorId() == color.getColorId()) {
                colorList.set(i, color);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(colorList);
        return result;
    }

    @Override
    public boolean delete(Integer integer) {
        List<Color> colorList = readFromfile();
        boolean returnData = false;
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getColorId() == integer) {
                colorList.get(i).setColorStatus(!colorList.get(i).isColorStatus());
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(colorList);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public List<Color> readFromfile() {
        List<Color> colorList = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_COLOR_FILE);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            colorList = (List<Color>) ois.readObject();
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
        return colorList;
    }

    @Override
    public boolean writeToFile(List<Color> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_COLOR_FILE);
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
    public Color inputData(Scanner sc) {
        List<Color> colorList = readFromfile();
        if (colorList == null) {
            colorList = new ArrayList<>();
        }
        Color colorNew = new Color();
        if (colorList.size() == 0) {
            colorNew.setColorId(1);
        } else {
            int max = 0;
            for (Color color : colorList) {
                if (max < color.getColorId()) {
                    max = color.getColorId();
                }
            }
            colorNew.setColorId(max + 1);
        }
        System.out.println("Nhập tên màu sắc vào");
        do {
            String colorName = sc.nextLine();
            if (colorName.trim().length() != 0 && colorName.trim() != "") {
                if (colorName.trim().length() >= 4 && colorName.trim().length() <= 30) {
                    boolean check = true;
                    for (Color color : colorList) {
                        if (color.getColorName().equals(colorName)) {
                            check = false;
                        }
                    }
                    if (check) {
                        colorNew.setColorName(colorName);
                        break;
                    } else {
                        System.err.println("Tên màu sắc đã bị trùng, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Vui lòng nhập tên màu sắc từ 4-30 ký tự");

                }
            } else {
                System.err.println("Tên màu sắc không được để trống");
            }
        } while (true);
        System.out.println("Nhập vào trạng thái màu sắc");
        System.out.println("1. Hoạt động");
        System.out.println("2. Không hoạt động");
        System.out.println("Lựa chọn của bạn là");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                colorNew.setColorStatus(true);
            } else if (choice == 2) {
                colorNew.setColorStatus(false);
            } else {
                System.err.println("Vui lòng chọn 1 hoặc 2");
            }
        } catch (NumberFormatException ex1) {
            System.err.println("Vui lòng nhập vào một số nguyên");
        }
        return colorNew;
    }

    @Override
    public void displayData(Color color) {
        String status = "Không hoạt động";
        if (color.isColorStatus()){
            status = "Hoạt động";
        }
        System.out.printf("%-10d%-50s%-20s\n", color.getColorId(),color.getColorName(),status);
    }

    @Override
    public List<Color> findAll() {
        return readFromfile();
    }

    @Override
    public Color findById(Integer id) {
        List<Color> colorList = readFromfile();
        for (Color color : colorList) {
            if (color.getColorId() == id) {
                return color;
            }
        }
        return null;
    }
}
