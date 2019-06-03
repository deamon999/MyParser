package com.gmail.deamon999;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class XmlWorker {

    void saveXml(File file, List<Car> carList) {
        if (file != null && carList != null) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = docBuilder.newDocument();
                Element root = document.createElement("Document");
                document.appendChild(root);
                for (Car car : carList) {
                    Element carEl = elementFromCar(car, document);
                    root.appendChild(carEl);
                }
                TransformerFactory traF = TransformerFactory.newInstance();
                Transformer transformer = traF.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult stRes = new StreamResult(file);
                transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(source, stRes);
            } catch (ParserConfigurationException | TransformerException e) {
                System.out.println("Document can not be crated!");
            }
        } else {
            System.out.println("Bad request!!!");
        }
    }

    List<Car> readXml(File file) {
        List<Car> carList = new ArrayList<>();
        if (file != null) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                Element root = document.getDocumentElement();
                NodeList cars = root.getChildNodes();
                for (int i = 0; i < cars.getLength(); i++) {
                    Node node = cars.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        Car car = getCarFromNode(element);
                        if (car != null) {
                            carList.add(car);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Document can not be crated!");
            }
        } else {
            System.out.println("Bad request!!!");
        }
        return carList;
    }

    private Car getCarFromNode(Element carElement) {
        if (!carElement.getTagName().equalsIgnoreCase("Car")) {
            return null;
        }
        String brandName = carElement.getElementsByTagName("BrandName").item(0).getTextContent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dateText = carElement.getElementsByTagName("Date").item(0).getTextContent();
        Date date = new Date();
        try {
            date = sdf.parse(dateText);
        } catch (ParseException e) {
            System.out.println("Error load date");
        }
        Node priceNode = carElement.getElementsByTagName("Price").item(0);
        int price = Integer.valueOf(priceNode.getTextContent());
        Car car = new Car(brandName, price, date);
        return car;
    }

    private Element elementFromCar(Car car, Document document) {
        Element carElement = document.createElement("Car");
        Element brandName = document.createElement("BrandName");
        brandName.setTextContent(car.getBranName());
        Element price = document.createElement("Price");
        price.setTextContent(String.valueOf(car.getPrice()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Element date = document.createElement("Date");
        date.setTextContent(sdf.format(car.getDate()));
        carElement.appendChild(date);
        carElement.appendChild(brandName);
        carElement.appendChild(price);
        return carElement;
    }
}
