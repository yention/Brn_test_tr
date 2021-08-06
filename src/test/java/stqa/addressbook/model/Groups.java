package stqa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Groups extends ForwardingSet<GroupData>{

    private Set<GroupData> delegate;

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Groups groupData) {
        this.delegate = new HashSet<GroupData>(groupData.delegate);
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set delegate() {
        return delegate;
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
