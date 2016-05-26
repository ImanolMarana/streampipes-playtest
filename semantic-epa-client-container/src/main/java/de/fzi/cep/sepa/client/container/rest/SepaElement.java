package de.fzi.cep.sepa.client.container.rest;

import java.util.List;

import javax.ws.rs.Path;

import de.fzi.cep.sepa.client.container.init.DeclarersSingleton;
import de.fzi.cep.sepa.client.declarer.SemanticEventProcessingAgentDeclarer;
import de.fzi.cep.sepa.model.impl.graph.SepaInvocation;

@Path("/sepa")
public class SepaElement extends InvocableElement<SepaInvocation, SemanticEventProcessingAgentDeclarer> {

    public SepaElement() {

        super(SepaInvocation.class);
    }

    @Override
    protected List<SemanticEventProcessingAgentDeclarer> getElementDeclarers() {
        return DeclarersSingleton.getInstance().getEpaDeclarers();
    }

}
