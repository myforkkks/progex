/*** In The Name of Allah ***/
package ghaffarian.progex.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ghaffarian.progex.graphs.pdg.ControlDependenceGraph;
import ghaffarian.progex.graphs.pdg.DataDependenceGraph;
import ghaffarian.progex.graphs.pdg.ProgramDependeceGraph;

/**
 * Program Dependence Graph (PDG) builder for Java programs.
 * A Java parser generated via ANTLRv4 is used for this purpose.
 * This implementation is based on ANTLRv4's Visitor pattern.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class JavaPDGBuilder {
	
	/**
	 * Builds and returns Program Dependence Graphs (PDG) for each given Java file.
	 */
	public static ProgramDependeceGraph[] buildForAll(String[] javaFilePaths) throws IOException {
		File[] javaFiles = new File[javaFilePaths.length];
		for (int i = 0; i < javaFiles.length; ++i)
			javaFiles[i] = new File(javaFilePaths[i]);
		return buildForAll(javaFiles);
	}
	
	/**
	 * Builds and returns Program Dependence Graphs (PDG) for given Java files.
	 */
	public static ProgramDependeceGraph[] buildForAll(File[] javaFiles) {
		ControlDependenceGraph[] ctrlSubgraphs;
		ctrlSubgraphs = new ControlDependenceGraph[javaFiles.length];
		for (int i = 0; i < javaFiles.length; ++i)
			try {
				ctrlSubgraphs[i] = JavaCDGBuilder.build(javaFiles[i]);
			} catch (Exception e) {
				ctrlSubgraphs[i] = null;
			}
        //
		DataDependenceGraph[] dataSubgraphs = new DataDependenceGraph[0];
		try {
			dataSubgraphs = JavaDDGBuilder.buildForAll(javaFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Join the subgraphs into PDGs
		ProgramDependeceGraph[] pdgArray = new ProgramDependeceGraph[javaFiles.length];
		for (int i=0; i< javaFiles.length; i++) {
			pdgArray[i] = new ProgramDependeceGraph(javaFiles[i], ctrlSubgraphs[i], dataSubgraphs[i]);
		}
		return pdgArray;
	}

}

