# InsightCoding
<h5>
In src/average_degree.java
</h5>
<p>
Method: readFrom <br/>
To get tweet input with file, reader, and get extracted information from original tweet information.
</p>
<p>
Method: writeTo<br/>
To write result into tweet output with file
</p>
<p>
Method: averageUpdate(List<Tweet> tweets)<br/>
Build up a Map&lt;String, Set&lt;String&gt;&gt; adjList, which is adjacent list of the Twitter hashtag graph.<br/>
Use a Queue to store the tweet input in sliding window of 60s.<br/>
If current tweet is inside window (no matter whether in order or not), add new hashtag into adjList, and then compute the average_degree.<br/>
if current tweet is outside window, then we need to remove from the first of queue, at the same time, remove corresponding edge from adjList.<br/>
</p>
