package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GitRepoObservers {
    public static Repository newRepository(){
        return new Repository() {
            List<WebHook> webHooks = new ArrayList<>();
            List<Event> events = new ArrayList<>();

            @Override
            public void addWebHook(WebHook webHook) {
                    webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Event event = new Event(Event.Type.COMMIT, branch, new ArrayList<>());
                events.add(event);
                event.commits().add(new Commit(author, changes));
                webHooks.stream().filter(w -> w.branch() == branch && w.type() == Event.Type.COMMIT).forEach(w -> w.onEvent(event) );
                return new Commit(author, changes);
            }
            @Override
            public void merge(String sourceBranch, String targetBranch) {
                Event event = new Event(Event.Type.MERGE, targetBranch, new ArrayList<>());

                boolean flag;
                for(Event sourceEvent: events)
                    if(Objects.equals(sourceEvent.branch(), sourceBranch) && sourceEvent.type() == Event.Type.COMMIT) {
                        for (Commit source : sourceEvent.commits()) {
                            flag = true;
                            for (Event targetEvent : events)
                                if (Objects.equals(targetEvent.branch(), targetBranch) && targetEvent.type() == Event.Type.MERGE)
                                    for (Commit target : targetEvent.commits())
                                        if (target.equals(source))
                                            flag = false;

                            if(flag)
                                event.commits().add(source);
                        }

                    }
                if(event.commits().size()>0) {
                    events.add(event);
                    webHooks.stream().filter(w -> Objects.equals(w.branch(), targetBranch) && w.type() == Event.Type.MERGE).forEach(w -> w.onEvent(event));
                }

            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHook() {
            List<Event> events = new ArrayList<>();
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                return events;
            }

            @Override
            public void onEvent(Event event) {
                events.add(event);
            }

        };
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHook() {
            List<Event> events = new ArrayList<>();
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {
                return events;
            }

            @Override
            public void onEvent(Event event) {
                events.add(event);
            }
        };
    }


}
