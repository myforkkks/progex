/*** In The Name of Allah ***/
package ghaffarian.progex.graphs.pdg;

import ghaffarian.progex.NodeType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class type of PDG nodes.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class PDNode {

	private Map<String, Object> properties;
	private Set<String> DEFs, USEs, selfFlows;

	public PDNode() {
		DEFs = new HashSet<>();
		USEs = new HashSet<>();
		selfFlows = new HashSet<>();
		properties = new HashMap<>();
	}

	public void setLineOfCode(int line) {
		properties.put("line", line);
	}

	public int getLineOfCode() {
		return (Integer) properties.get("line");
	}

	public void setCode(String code) {
		properties.put("code", code);
	}

	// FIXME: 2022/3/22
	public String getCode() {
		String s = (String) properties.get("code");
		s = s.replaceAll("<.*>", "");
		if (s.endsWith(";"))
			s = s.substring(0, s.length()-1);
		if (s.endsWith(":"))
			s = s.substring(0, s.length()-1);
		return s;
	}

	public boolean addDEF(String var) {
		return DEFs.add(var);
	}

	public boolean hasDEF(String var) {
		return DEFs.contains(var);
	}

	public String[] getAllDEFs() {
		return DEFs.toArray(new String[DEFs.size()]);
	}

	public boolean addUSE(String var) {
		return USEs.add(var);
	}

	public boolean hasUSE(String var) {
		return USEs.contains(var);
	}

	public String[] getAllUSEs() {
		return USEs.toArray(new String[USEs.size()]);
	}

	public boolean addSelfFlow(String var) {
		return selfFlows.add(var);
	}

	public String[] getAllSelfFlows() {
		return selfFlows.toArray(new String[selfFlows.size()]);
	}

	public void setProperty(String key, Object value) {
		properties.put(key.toLowerCase(), value);
	}

	public Object getProperty(String key) {
		return properties.get(key.toLowerCase());
	}

	public Set<String> getAllProperties() {
		return properties.keySet();
	}

	public void setType(NodeType type) {
		properties.put("type", type);
	}

	public NodeType getType() {
		return (NodeType) properties.get("type");
	}

	public void setBranch(boolean flag) {
		properties.put("branch", flag);
	}

	public boolean isBranch() {
		return (boolean) properties.get("branch");
	}

	public void setTerminal(boolean flag) {
		properties.put("terminal", flag);
	}

	public boolean isTerminal() {
		return (boolean) properties.get("terminal");
	}

	public String getUid() {
		return getCode() + "$fyyy$" + getLineOfCode();
	}

	@Override
	public String toString() {
		int line = (Integer) properties.get("line");
		String code = (String) properties.get("code");
		return (line + ": " + code);
	}

}
