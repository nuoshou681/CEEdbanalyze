export default {
    fetchStreamData(param, onMessage, onError) {
        const eventSource = new EventSource(`http://124.223.63.238:5173/ai/steamout?message=${encodeURIComponent(param)}`);
        eventSource.onmessage = (event) => {
            if (onMessage) {
                onMessage(event.data);
            }
        };
        eventSource.onerror = (event) => {
            if (onError) {
                onError(event);
            }
            eventSource.close();
        };
        return eventSource;
    }
}