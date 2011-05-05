package de.myToys.shop.maven.plugins.debug;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.shared.dependency.tree.DependencyNode;
import org.apache.maven.shared.dependency.tree.traversal.DependencyNodeVisitor;

/**
 *
 * @author lehrenfried
 */
public class ConflictDependencyNodeVisitor implements DependencyNodeVisitor {

	SetMultimap<String, DependencyNode> dependencies = LinkedHashMultimap.create();
	Writer writer;

	public ConflictDependencyNodeVisitor(Writer writer) {
		this.writer = writer;
	}

	public boolean visit(DependencyNode dn) {
		try {
			String ga = dn.getArtifact().getGroupId() + ':' + dn.getArtifact().getArtifactId();
			dependencies.put(ga, dn);
			writer.append(ga);
			writer.append('\n');
		} catch (IOException ex) {
			Logger.getLogger(ConflictDependencyNodeVisitor.class.getName()).log(Level.SEVERE, null, ex);
		}
		return true;
	}

	public boolean endVisit(DependencyNode dn) {
		return true;
	}
}
