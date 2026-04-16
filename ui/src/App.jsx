import { useState } from "react";

function App() {
  const [code, setCode] = useState("");
  const [language, setLanguage] = useState("Java");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleReview = async () => {
    if (!code) {
      alert("Paste some code first 😏");
      return;
    }

    setLoading(true);
    setResult(null);

    try {
      const res = await fetch("http://localhost:8080/ai/review", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ code, language })
      });

      const data = await res.json();
      setResult(data);
    } catch (err) {
      console.error(err);
      alert("Backend error ⚠️");
    }

    setLoading(false);
  };

  return (
    <div style={{
      minHeight: "100vh",
      background: "linear-gradient(135deg, #0f2027, #203a43, #2c5364)",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      color: "white",
      fontFamily: "Poppins, sans-serif"
    }}>
      <div style={{
        width: "900px",
        padding: "25px",
        borderRadius: "15px",
        background: "rgba(255,255,255,0.05)",
        backdropFilter: "blur(10px)",
        boxShadow: "0 0 30px rgba(0,0,0,0.5)"
      }}>
        <h1 style={{ textAlign: "center", marginBottom: "20px" }}>
          ⚡ AI Code Reviewer
        </h1>

        {/* CODE INPUT */}
        <textarea
          rows="10"
          value={code}
          onChange={(e) => setCode(e.target.value)}
          placeholder="Paste your code here..."
          style={{
            width: "100%",
            padding: "15px",
            borderRadius: "10px",
            border: "none",
            outline: "none",
            background: "#111",
            color: "#0f0",
            fontFamily: "monospace",
            fontSize: "14px"
          }}
        />

        <br /><br />

        {/* CONTROLS */}
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <select
            value={language}
            onChange={(e) => setLanguage(e.target.value)}
            style={{
              padding: "10px",
              borderRadius: "8px",
              border: "none"
            }}
          >
            <option>Java</option>
            <option>JavaScript</option>
            <option>Python</option>
            <option>C++</option>
          </select>

          <button
            onClick={handleReview}
            style={{
              padding: "10px 25px",
              borderRadius: "8px",
              border: "none",
              background: "linear-gradient(45deg, #00ffcc, #00ccff)",
              color: "black",
              fontWeight: "bold",
              cursor: "pointer",
              boxShadow: "0 0 15px #00ffcc"
            }}
          >
            🚀 Review Code
          </button>
        </div>

        <br />

        {/* LOADING */}
        {loading && (
          <p style={{ textAlign: "center", color: "#00ffcc" }}>
            ⏳ AI is analyzing your code...
          </p>
        )}

        {/* RESULT */}
        {result && (
          <div style={{
            marginTop: "20px",
            padding: "20px",
            borderRadius: "10px",
            background: "#111",
            boxShadow: "0 0 15px rgba(0,255,200,0.3)"
          }}>
            <h2>📊 Result</h2>

            <p><b>🐞 Bugs:</b> {result.bugs}</p>
            <p><b>💡 Improvements:</b> {result.improvements}</p>
            <p><b>⚡ Optimization:</b> {result.optimization}</p>
            <p><b>🧹 Clean Code:</b> {result.cleanCode}</p>
            <p><b>⭐ Rating:</b> {result.rating}</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;