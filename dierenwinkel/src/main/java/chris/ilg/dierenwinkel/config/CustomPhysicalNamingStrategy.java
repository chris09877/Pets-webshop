package chris.ilg.dierenwinkel.config;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy extends org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl{

        @Override
        public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
            return name;
        }


}
