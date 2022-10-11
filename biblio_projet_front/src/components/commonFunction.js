export function formatDate(sDate) {
  return (
    sDate.substr(8, 2) + "/" + sDate.substr(5, 2) + "/" + sDate.substr(0, 4)
  );
}
