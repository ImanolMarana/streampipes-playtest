/**
 * boilerpipe
 * <p>
 * Copyright (c) 2009, 2014 Christian Kohlschütter
 * <p>
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kohlschutter.boilerpipe.filters.heuristics;

import com.kohlschutter.boilerpipe.BoilerpipeFilter;
import com.kohlschutter.boilerpipe.BoilerpipeProcessingException;
import com.kohlschutter.boilerpipe.document.TextBlock;
import com.kohlschutter.boilerpipe.document.TextDocument;

import java.util.Iterator;
import java.util.List;

/**
 * Fuses adjacent blocks if their distance (in blocks) does not exceed a certain limit. This
 * probably makes sense only in cases where an upstream filter already has removed some blocks.
 */
public final class BlockProximityFusion implements BoilerpipeFilter {

  private final int maxBlocksDistance;

  public static final BlockProximityFusion MAX_DISTANCE_1 = new BlockProximityFusion(1, false,
      false);
  public static final BlockProximityFusion MAX_DISTANCE_1_SAME_TAGLEVEL = new BlockProximityFusion(
      1, false, true);
  public static final BlockProximityFusion MAX_DISTANCE_1_CONTENT_ONLY = new BlockProximityFusion(
      1, true, false);
  public static final BlockProximityFusion MAX_DISTANCE_1_CONTENT_ONLY_SAME_TAGLEVEL =
      new BlockProximityFusion(1, true, true);

  private final boolean contentOnly;

  private final boolean sameTagLevelOnly;

  /**
   * Creates a new {@link BlockProximityFusion} instance.
   *
   * @param maxBlocksDistance The maximum distance in blocks.
   * @param contentOnly
   */
  public BlockProximityFusion(final int maxBlocksDistance, final boolean contentOnly,
                              final boolean sameTagLevelOnly) {
    this.maxBlocksDistance = maxBlocksDistance;
    this.contentOnly = contentOnly;
    this.sameTagLevelOnly = sameTagLevelOnly;
  }

  public boolean process(TextDocument doc) throws BoilerpipeProcessingException {
  List<TextBlock> textBlocks = doc.getTextBlocks();
  if (textBlocks.size() < 2) {
    return false;
  }

  return mergeBlocks(textBlocks);
}

private boolean mergeBlocks(List<TextBlock> textBlocks) {
  boolean changes = false;
  TextBlock prevBlock = findFirstContentBlock(textBlocks);
  if (prevBlock == null) {
    return false;
  }

  int offset = contentOnly ? findFirstContentBlockIndex(textBlocks) + 1 : 1;

  for (Iterator<TextBlock> it = textBlocks.listIterator(offset); it.hasNext(); ) {
    TextBlock block = it.next();
    if (!block.isContent()) {
      prevBlock = block;
      continue;
    }

    if (shouldMergeBlocks(prevBlock, block)) {
      prevBlock.mergeNext(block);
      it.remove();
      changes = true;
    } else {
      prevBlock = block;
    }
  }
  return changes;
}

private TextBlock findFirstContentBlock(List<TextBlock> textBlocks) {
  if (!contentOnly) {
    return textBlocks.get(0);
  }
  for (TextBlock tb : textBlocks) {
    if (tb.isContent()) {
      return tb;
    }
  }
  return null;
}

private int findFirstContentBlockIndex(List<TextBlock> textBlocks) {
  int offset = 0;
  for (TextBlock tb : textBlocks) {
    if (tb.isContent()) {
      return offset;
    }
    offset++;
  }
  return offset;
}

private boolean shouldMergeBlocks(TextBlock prevBlock, TextBlock block) {
  int diffBlocks = block.getOffsetBlocksStart() - prevBlock.getOffsetBlocksEnd() - 1;
  if (diffBlocks > maxBlocksDistance) {
    return false;
  }
  if (contentOnly && (!prevBlock.isContent() || !block.isContent())) {
    return false;
  }
  return !sameTagLevelOnly || prevBlock.getTagLevel() == block.getTagLevel();
}

//Refactoring end

}
