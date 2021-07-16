package stqa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData>{

    private Set<GroupData> delegate;

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Groups groupData) {
        this.delegate = new HashSet<GroupData>(groupData);
    }

    @Override
    protected Set<GroupData> delegate() {
        return null;
    }

    public Groups withAdded(GroupData groupData){
        Groups groups = new Groups(this);
        groups.add(groupData);
        return groups;
    }

    public Groups without(GroupData groupData){
        Groups groups = new Groups(this);
        groups.remove(groupData);
        return groups;
    }
}
