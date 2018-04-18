Effective Java task

Let's take as the base of our homework "Effective Java" book by Joshua Bloch.

1) You should implement 5 different items (e.g. Item 25: Prefer lists to arrays, etc.) from 5 different sections (e.g. Section 5: Generics) in your own interpretation and in your own example. The main idea here is to understand why we should use such items and in what cases. You could have a talk with your mentor with ideas of your implementation. We don't need here fully working applications. The task could be done in the scope of several classes. Don't use examples from the book. It should be you own examples. (1 point per item)

2) Implement cache service. cache.JavaCache entries (objects) – simple custom class with one String field. Your cache service should have 2 methods:

RETURN_TYPE get(LIST_OF_PARAMETERS);

RETURN_TYPE put(LIST_OF_PARAMETERS);

Your cache service should fit next requirements:

· Max Size = 100 000

· Strategy: LFU (last frequently used)

· Eviction policy

· Time-based on last access (5 seconds)

· Removal listener

· Just add to log of removed entry

· Give statistic to user

· Average time spent for putting new values into cache

· Number of cache evictions

· Support concurrency

This task should be implemented in two ways:

1. Simple Java (3 points)

2. Guava (2 points)

Don't forget about good tests, checkstyle and other staff that already included into your build phase.
