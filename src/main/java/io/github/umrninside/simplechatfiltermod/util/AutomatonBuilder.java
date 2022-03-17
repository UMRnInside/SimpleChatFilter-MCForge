package io.github.umrninside.simplechatfiltermod.util;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import javax.annotation.Nonnull;
import java.util.List;

public class AutomatonBuilder {
    private Trie trie;
    public AutomatonBuilder() {
        // Empty trie
        trie = Trie.builder().build();
    }
    
    public AutomatonBuilder(@Nonnull List<String> strings) {
        this.rebuildTrie(strings);
    }
    
    public synchronized void rebuildTrie(@Nonnull List<String> strings) {
        Trie.TrieBuilder builder = Trie.builder();
        for (String s: strings) {
            builder.addKeyword(s);
        }
        this.trie = builder.build();
    }
    
    public boolean hasMatchedAny(String source) {
        return trie.containsMatch(source);
    }
}
