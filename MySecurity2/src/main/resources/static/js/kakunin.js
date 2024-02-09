 <script th:inline="javascript">
        /*<![CDATA[*/
        document.getElementById('deleteButton').addEventListener('click', function() {
            if (confirm('この商品を削除してもよろしいですか？')) {
                document.getElementById('deleteForm').submit();
            }
        });
        /*]]>*/
    </script>