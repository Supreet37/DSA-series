/**
 * LeetCode 2095 - Delete the Middle Node of a Linked List
 *
 * Approach:
 * - Use Slow and Fast Pointer technique.
 * - Slow moves one step at a time.
 * - Fast moves two steps at a time.
 * - When Fast reaches the end, Slow will be just before
 *   the middle node.
 * - Delete the middle node by skipping it.
 *
 * Example:
 * 1 -> 3 -> 4 -> 7 -> 1 -> 2 -> 6
 *
 * slow stops at 4
 * middle node = 7
 *
 * After deletion:
 * 1 -> 3 -> 4 -> 1 -> 2 -> 6
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


#include <stdio.h>
#include <stdlib.h>

// Definition for singly-linked list
struct ListNode {
    int val;
    struct ListNode *next;
};

// Function to delete the middle node
struct ListNode* deleteMiddle(struct ListNode* head) {

    if (head == NULL || head->next == NULL)
        return NULL;

    struct ListNode *slow = head;
    struct ListNode *fast = head->next;

    while (fast->next && fast->next->next) {
        slow = slow->next;
        fast = fast->next->next;
    }

    struct ListNode *temp = slow->next;
    slow->next = slow->next->next;
    free(temp);

    return head;
}

// Function to create a new node
struct ListNode* createNode(int val) {
    struct ListNode* node = (struct ListNode*)malloc(sizeof(struct ListNode));
    node->val = val;
    node->next = NULL;
    return node;
}

// Function to print the linked list
void printList(struct ListNode* head) {
    while (head) {
        printf("%d", head->val);
        if (head->next)
            printf(" -> ");
        head = head->next;
    }
    printf("\n");
}

int main() {

    // Example: 1 -> 3 -> 4 -> 7 -> 1 -> 2 -> 6

    struct ListNode* head = createNode(1);
    head->next = createNode(3);
    head->next->next = createNode(4);
    head->next->next->next = createNode(7);
    head->next->next->next->next = createNode(1);
    head->next->next->next->next->next = createNode(2);
    head->next->next->next->next->next->next = createNode(6);

    printf("Original List:\n");
    printList(head);

    head = deleteMiddle(head);

    printf("After Deleting Middle Node:\n");
    printList(head);

    return 0;
}


/*
Idea:
- Use two pointers: slow and fast.
- Fast moves twice as fast as slow.
- When fast reaches the end, slow reaches the node
  just before the middle.
- Remove the middle node by changing links.

Time Complexity: O(n)
Space Complexity: O(1)
*/