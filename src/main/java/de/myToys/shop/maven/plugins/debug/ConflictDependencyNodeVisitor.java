package de.myToys.shop.maven.plugins.debug;

import org.apache.maven.shared.dependency.tree.DependencyNode;
import org.apache.maven.shared.dependency.tree.traversal.DependencyNodeVisitor;

/**
 *
 * @author lehrenfried
 */
public class ConflictDependencyNodeVisitor implements DependencyNodeVisitor{

	public boolean visit(DependencyNode dn) {
		dn.getArtifact().getArtifactId();
		dn.getArtifact().getGroupId();
		
		return true;
	}

	public boolean endVisit(DependencyNode dn) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
