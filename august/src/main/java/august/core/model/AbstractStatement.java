package august.core.model;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;

import negofast.core.model.IStatement;
import negofast.core.model.Subject;

public abstract class AbstractStatement implements IStatement {
    private Collection<URI> language;
    private Subject subject;

    public AbstractStatement(URI lang, Subject subj) {
    	language = new HashSet<URI>();
    	language.add(lang);
    	subject = subj;
    }

    public Collection<URI> getLanguage() {
        return language;
    }

    public void setLanguage(Collection<URI> val) {
        this.language = val;
    }
    
    public Subject getSubject() {
    	return subject;
    }
}
