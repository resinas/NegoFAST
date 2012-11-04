/*
 * SimpleCommitHandler.java
 *
 * Created on August 9, 2007, 11:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.commithandler.simple;

import java.net.URI;

import negofast.core.interactions.requestcommitapproval.ApprovalType;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestcommitapproval.ICommitRequester;
import negofast.core.model.IProposal;

/**
 *
 * @author resman
 */
public class SimpleCommitHandler implements ICommitHandler<IProposal<?>> {
    
    /**
     * Creates a new instance of SimpleCommitHandler
     */
    public SimpleCommitHandler() {
    }

    public void approvalRequest(URI thread, IProposal<?> p, ApprovalType t, ICommitRequester requester) {
        requester.approved(thread);
    }

    public void fail(URI thread) {
    }

    public void success(URI thread) {
    }
    
}
