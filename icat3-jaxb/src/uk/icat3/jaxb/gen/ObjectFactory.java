//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-382 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.09.17 at 03:36:06 PM BST 
//


package uk.icat3.jaxb.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uk.icat3.jaxb.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Icat_QNAME = new QName("", "icat");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.icat3.jaxb.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Publication }
     * 
     */
    public Publication createPublication() {
        return new Publication();
    }

    /**
     * Create an instance of {@link Topic }
     * 
     */
    public Topic createTopic() {
        return new Topic();
    }

    /**
     * Create an instance of {@link Datafile }
     * 
     */
    public Datafile createDatafile() {
        return new Datafile();
    }

    /**
     * Create an instance of {@link Investigation }
     * 
     */
    public Investigation createInvestigation() {
        return new Investigation();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Icat }
     * 
     */
    public Icat createIcat() {
        return new Icat();
    }

    /**
     * Create an instance of {@link Sample }
     * 
     */
    public Sample createSample() {
        return new Sample();
    }

    /**
     * Create an instance of {@link Investigator }
     * 
     */
    public Investigator createInvestigator() {
        return new Investigator();
    }

    /**
     * Create an instance of {@link Study }
     * 
     */
    public Study createStudy() {
        return new Study();
    }

    /**
     * Create an instance of {@link Dataset }
     * 
     */
    public Dataset createDataset() {
        return new Dataset();
    }

    /**
     * Create an instance of {@link Keyword }
     * 
     */
    public Keyword createKeyword() {
        return new Keyword();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Icat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "icat")
    public JAXBElement<Icat> createIcat(Icat value) {
        return new JAXBElement<Icat>(_Icat_QNAME, Icat.class, null, value);
    }

}
