import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import java.util.Collections;

public class ProfessoriOxford
{
    public static void main( String[] args )
    {
        String sparqlEndpoint = "https://query.wikidata.org/sparql";
        SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);

        String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
        repo.setAdditionalHttpHeaders( Collections.singletonMap("User-Agent", userAgent ) );

        String querySelect = "SELECT ?nomeLabel ?cognomeLabel ?universitàLabel WHERE {\n" +
                "  ?persona wdt:P2561 ?nome.\n" +
                "  ?persona wdt:P734 ?cognome.\n" +
                "  ?persona wdt:P108 ?università.\n" +
                "  ?persona wdt:P108 wd:Q34433\n" +
                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],mul,en\". }\n" +
                "}";

        try{
            repo.getConnection().prepareTupleQuery(querySelect).evaluate(new SPARQLResultsJSONWriter(System.out));
        } catch ( Exception exception ) {
            exception.printStackTrace();
        }

    }
}
