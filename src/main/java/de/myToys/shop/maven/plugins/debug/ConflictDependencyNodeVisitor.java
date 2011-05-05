package de.myToys.shop.maven.plugins.debug;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
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
		if (writer != null) {
			throw new UnsupportedOperationException("this visitor does not use the writer, please call #getConflictInfo() after #visit()");
		}
	}

	public boolean visit(DependencyNode dn) {
		String ga = dn.getArtifact().getGroupId() + ':' + dn.getArtifact().getArtifactId();
		dependencies.put(ga, dn);
		return true;
	}

	public boolean endVisit(DependencyNode dn) {
		return true;
	}

	public String getConflictInfo() {
		StringBuilder buf = new StringBuilder();
		for (String key : dependencies.keySet()) {
			if (getDifferingVersions(key) > 1) {
				buf.append('\n');
				buf.append("===");
				buf.append(key);
				buf.append("===");
				buf.append('\n');
				for (DependencyNode dn : dependencies.get(key)) {
					buf.append(dn.getArtifact().getArtifactId());
					buf.append(':');
					buf.append(dn.getArtifact().getVersionRange());
					buf.append('\n');
				}
			}
		}
		return buf.toString();
	}

	private int getDifferingVersions(String key) {
		Set<String> versions = new HashSet();
		for(DependencyNode dn : dependencies.get(key)){
			versions.add(dn.getArtifact().getVersion());
		}
		
		return versions.size();
	}
}
