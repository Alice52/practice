package top.hubby.coding.elseif.strategy1.annotation;

import java.lang.annotation.Annotation;

/**
 * This class should have equals and hashCode method: <br>
 * and should same implement as $proxy class.
 *
 * @author zack <br>
 * @create 2020-12-29<br>
 * @project common-coding <br>
 */
public class HandlerTypeImpl implements HandlerType {

    private String source;
    private String pay;

    public HandlerTypeImpl(String source, String pay) {
        this.source = source;
        this.pay = pay;
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public String pay() {
        return pay;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return HandlerType.class;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += (127 * "source".hashCode()) ^ source.hashCode();
        hashCode += (127 * "pay".hashCode()) ^ pay.hashCode();
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HandlerType)) {
            return false;
        }
        HandlerType other = (HandlerType) obj;
        return source.equals(other.source()) && pay.equals(other.pay());
    }
}
